/*
 * Copyright (C) 2012 CyberAgent
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.co.cyberagent.android.gpuimage.sample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import jp.co.cyberagent.android.gpuimage.*;

import java.util.LinkedList;
import java.util.List;

public class GPUImageFilterTools {  
    public static void showDialog(final Context context,
            final OnGpuImageFilterChosenListener listener) {
        final FilterList filters = new FilterList();
        
        filters.addFilter("Invert", FilterType.INVERT);
        filters.addFilter("Pixelation", FilterType.PIXELATION);
        filters.addFilter("Hue", FilterType.HUE);
     

        filters.addFilter("Grayscale", FilterType.GRAYSCALE);
        filters.addFilter("Sharpness", FilterType.SHARPEN);
        filters.addFilter("Sobel Edge Detection", FilterType.SOBEL_EDGE_DETECTION);
       
        filters.addFilter("Emboss", FilterType.EMBOSS);
        filters.addFilter("Posterize", FilterType.POSTERIZE);
        filters.addFilter("Monochrome", FilterType.MONOCHROME);

        filters.addFilter("Laplacian", FilterType.LAPLACIAN); 
        filters.addFilter("Vignette", FilterType.VIGNETTE);
        filters.addFilter("Crosshatch", FilterType.CROSSHATCH);
  
        filters.addFilter("Box Blur", FilterType.BOX_BLUR);
        filters.addFilter("CGA Color Space", FilterType.CGA_COLORSPACE);
        filters.addFilter("Glass Sphere", FilterType.GLASS_SPHERE);
     
        filters.addFilter("Sketch", FilterType.SKETCH);
        filters.addFilter("Toon", FilterType.TOON);
        filters.addFilter("Smooth Toon", FilterType.SMOOTH_TOON);
                
        filters.addFilter("Weak Pixel Inclusion", FilterType.WEAK_PIXEL_INCLUSION);
        filters.addFilter("False Color", FilterType.FALSE_COLOR);
        filters.addFilter("Color Balance", FilterType.COLOR_BALANCE);
        filters.addFilter("3*3", FilterType.THREE_X_THREE_CONVOLUTION);

      
      


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose a filter");
        builder.setItems(filters.names.toArray(new String[filters.names.size()]),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int item) {
                        listener.onGpuImageFilterChosenListener(
                                createFilterForType(context, filters.filters.get(item)));
                    }
                });
        builder.create().show();
    }

    private static GPUImageFilter createFilterForType(final Context context, final FilterType type) {
        switch (type) {
        
        case INVERT:
            return new GPUImageColorInvertFilter();
        case PIXELATION:
            return new GPUImagePixelationFilter();
        case HUE:
            return new GPUImageHueFilter(90.0f);
            
            
        case GRAYSCALE:
            return new GPUImageGrayscaleFilter();
        case SHARPEN:
            GPUImageSharpenFilter sharpness = new GPUImageSharpenFilter();
            sharpness.setSharpness(2.0f);
            return sharpness;
        case SOBEL_EDGE_DETECTION:
            return new GPUImageSobelEdgeDetection();
            
            
        case EMBOSS:
            return new GPUImageEmbossFilter();
        case POSTERIZE:
            return new GPUImagePosterizeFilter();
        case MONOCHROME:
        	return new GPUImageMonochromeFilter(1.0f, new float[]{0.6f, 0.45f, 0.3f, 1.0f});
        
     
        case VIGNETTE:
            PointF centerPoint = new PointF();
            centerPoint.x = 0.5f;
            centerPoint.y = 0.5f;
            return new GPUImageVignetteFilter(centerPoint, new float[] {0.0f, 0.0f, 0.0f}, 0.3f, 0.75f);
        case LAPLACIAN:
            return new GPUImageLaplacianFilter();
        case CROSSHATCH:
            return new GPUImageCrosshatchFilter();  
            
      
        case BOX_BLUR:
            return new GPUImageBoxBlurFilter();
        case CGA_COLORSPACE:
            return new GPUImageCGAColorspaceFilter();
        case GLASS_SPHERE:
            return new GPUImageGlassSphereFilter();   
            
           
        case SKETCH:
            return new GPUImageSketchFilter();
        case TOON:
            return new GPUImageToonFilter();
        case SMOOTH_TOON:
            return new GPUImageSmoothToonFilter();

        case WEAK_PIXEL_INCLUSION:
            return new GPUImageWeakPixelInclusionFilter();
        case FALSE_COLOR:
            return new GPUImageFalseColorFilter(); 
        case COLOR_BALANCE:
            return new GPUImageColorBalanceFilter();
        case THREE_X_THREE_CONVOLUTION:
          GPUImage3x3ConvolutionFilter convolution = new GPUImage3x3ConvolutionFilter();
          convolution.setConvolutionKernel(new float[] {
        		 
                  -1.0f, 0.0f, 1.0f,
                  -2.0f, 0.0f, 2.0f,
                  -1.0f, 0.0f, 1.0f
          });
          return convolution;
   
        
        
//        case CONTRAST:
//                return new GPUImageContrastFilter(2.0f);
//            case GAMMA:
//                return new GPUImageGammaFilter(2.0f);
//           
//            case BRIGHTNESS:
//                return new GPUImageBrightnessFilter(1.5f);
//           
//            case SEPIA:
//                return new GPUImageSepiaFilter();
//           
//           
//          
//            case FILTER_GROUP:
//                List<GPUImageFilter> filters = new LinkedList<GPUImageFilter>();
//                filters.add(new GPUImageContrastFilter());
//                filters.add(new GPUImageDirectionalSobelEdgeDetectionFilter());
//                filters.add(new GPUImageGrayscaleFilter());
//                return new GPUImageFilterGroup(filters);
//            case SATURATION:
//                return new GPUImageSaturationFilter(1.0f);
//            case EXPOSURE:
//                return new GPUImageExposureFilter(0.0f);
//            case HIGHLIGHT_SHADOW:
//            	return new GPUImageHighlightShadowFilter(0.0f, 1.0f);
//         
//            case OPACITY:
//                return new GPUImageOpacityFilter(1.0f);  
//            case RGB:
//                return new GPUImageRGBFilter(1.0f, 1.0f, 1.0f);  
//            case WHITE_BALANCE:
//                return new GPUImageWhiteBalanceFilter(5000.0f, 0.0f);    
//           
//            case TONE_CURVE:
//                GPUImageToneCurveFilter toneCurveFilter = new GPUImageToneCurveFilter();
//                toneCurveFilter.setFromCurveFileInputStream(
//                        context.getResources().openRawResource(R.raw.tone_cuver_sample));
//                return toneCurveFilter;
//          
//            case GAUSSIAN_BLUR:
//                return new GPUImageGaussianBlurFilter();
//           
//
//           
//            case DILATION:
//                return new GPUImageDilationFilter();
//            case KUWAHARA:
//                return new GPUImageKuwaharaFilter();
//            case RGB_DILATION:
//                return new GPUImageRGBDilationFilter();
//           
//            case BULGE_DISTORTION:
//                return new GPUImageBulgeDistortionFilter();
//            
//            case HAZE:
//                return new GPUImageHazeFilter();
//           
//            case NON_MAXIMUM_SUPPRESSION:
//                return new GPUImageNonMaximumSuppressionFilter();
//            case SPHERE_REFRACTION:
//                return new GPUImageSphereRefractionFilter();
//            case SWIRL:
//                return new GPUImageSwirlFilter();
//          
//         
//            case LEVELS_FILTER_MIN:
//                GPUImageLevelsFilter levelsFilter = new GPUImageLevelsFilter();
//                levelsFilter.setMin(0.0f, 3.0f, 1.0f);
//                return levelsFilter;
//
//            
            default:
                throw new IllegalStateException("No filter of that type!");
        }

    }

    private static GPUImageFilter createBlendFilter(Context context, Class<? extends GPUImageTwoInputFilter> filterClass) {
        try {
            GPUImageTwoInputFilter filter = filterClass.newInstance();
            filter.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
            return filter;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public interface OnGpuImageFilterChosenListener {
        void onGpuImageFilterChosenListener(GPUImageFilter filter);
    }

    private enum FilterType {
    							INVERT,PIXELATION,HUE,              	GRAYSCALE,SHARPEN,SOBEL_EDGE_DETECTION,
    							EMBOSS,POSTERIZE,MONOCHROME,             LAPLACIAN,CROSSHATCH, VIGNETTE, 
    							BOX_BLUR,CGA_COLORSPACE,GLASS_SPHERE,    SKETCH,TOON,SMOOTH_TOON,
    							WEAK_PIXEL_INCLUSION, FALSE_COLOR,COLOR_BALANCE,THREE_X_THREE_CONVOLUTION
       
    }

    private static class FilterList {
        public List<String> names = new LinkedList<String>();
        public List<FilterType> filters = new LinkedList<FilterType>();

        public void addFilter(final String name, final FilterType filter) {
            names.add(name);
            filters.add(filter);
        }
    }

    public static class FilterAdjuster {
        private final Adjuster<? extends GPUImageFilter> adjuster;

        public FilterAdjuster(final GPUImageFilter filter) {
            if (filter instanceof GPUImageSharpenFilter) {
                adjuster = new SharpnessAdjuster().filter(filter);
            } else if (filter instanceof GPUImageSepiaFilter) {
                adjuster = new SepiaAdjuster().filter(filter);
            } else if (filter instanceof GPUImageContrastFilter) {
                adjuster = new ContrastAdjuster().filter(filter);
            } 
            else if (filter instanceof GPUImageSobelEdgeDetection) {
                adjuster = new SobelAdjuster().filter(filter);
            }
            else if (filter instanceof GPUImageEmbossFilter) { 
                adjuster = new EmbossAdjuster().filter(filter);
            }
            else if (filter instanceof GPUImage3x3TextureSamplingFilter) {
             
            	adjuster = new GPU3x3TextureAdjuster().filter(filter);
            } else if (filter instanceof GPUImageHueFilter) {
                adjuster = new HueAdjuster().filter(filter);
            }
            else if (filter instanceof GPUImagePosterizeFilter) {
                adjuster = new PosterizeAdjuster().filter(filter);
            } 
//            else if (filter instanceof GPUImagePixelationFilter) {
//                adjuster = new PixelationAdjuster().filter(filter);
//            } 
            else if (filter instanceof GPUImageHighlightShadowFilter) {
                adjuster = new HighlightShadowAdjuster().filter(filter);
            } 
            else if (filter instanceof GPUImageMonochromeFilter) {
                adjuster = new MonochromeAdjuster().filter(filter);
            } 
            else if (filter instanceof GPUImageVignetteFilter) {
                adjuster = new VignetteAdjuster().filter(filter);
            }
            else if (filter instanceof GPUImageDissolveBlendFilter) {
                adjuster = new DissolveBlendAdjuster().filter(filter);
            }
            else if (filter instanceof GPUImageCrosshatchFilter) {
                adjuster = new CrosshatchBlurAdjuster().filter(filter);
            } 
            else if (filter instanceof GPUImageGlassSphereFilter) {
                adjuster = new GlassSphereAdjuster().filter(filter);
            } 
          
            else {

                adjuster = null;
            }
        }

        public boolean canAdjust() {
            return adjuster != null;
        }

        public void adjust(final int percentage) {
            if (adjuster != null) {
                adjuster.adjust(percentage);
            }
        }

        private abstract class Adjuster<T extends GPUImageFilter> {
            private T filter;

            @SuppressWarnings("unchecked")
            public Adjuster<T> filter(final GPUImageFilter filter) {
                this.filter = (T) filter;
                return this;
            }

            public T getFilter() {
                return filter;
            }

            public abstract void adjust(int percentage);

            protected float range(final int percentage, final float start, final float end) {
                return (end - start) * percentage / 100.0f + start;
            }

            protected int range(final int percentage, final int start, final int end) {
                return (end - start) * percentage / 100 + start;
            }
        }

        private class SharpnessAdjuster extends Adjuster<GPUImageSharpenFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setSharpness(range(percentage, -4.0f, 4.0f));
            }
        }

//        private class PixelationAdjuster extends Adjuster<GPUImagePixelationFilter> {
//          @Override
//          public void adjust(final int percentage) {
//              getFilter().setPixel(range(percentage, 1.0f, 100.0f));
//          }
//        }

        private class HueAdjuster extends Adjuster<GPUImageHueFilter> {
          @Override
          public void adjust(final int percentage) {
            getFilter().setHue(range(percentage, 0.0f, 360.0f));
          }
        }

        private class ContrastAdjuster extends Adjuster<GPUImageContrastFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setContrast(range(percentage, 0.0f, 2.0f));
            }
        }

        private class GammaAdjuster extends Adjuster<GPUImageGammaFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setGamma(range(percentage, 0.0f, 3.0f));
            }
        }

        private class BrightnessAdjuster extends Adjuster<GPUImageBrightnessFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setBrightness(range(percentage, -1.0f, 1.0f));
            }
        }

        private class SepiaAdjuster extends Adjuster<GPUImageSepiaFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setIntensity(range(percentage, 0.0f, 2.0f));
            }
        }

        private class SobelAdjuster extends Adjuster<GPUImageSobelEdgeDetection> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setLineSize(range(percentage, 0.0f, 5.0f));
            }
        }

        private class EmbossAdjuster extends Adjuster<GPUImageEmbossFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setIntensity(range(percentage, 0.0f, 4.0f));
            }
        }

        private class PosterizeAdjuster extends Adjuster<GPUImagePosterizeFilter> {
            @Override
            public void adjust(final int percentage) {
                // In theorie to 256, but only first 50 are interesting
                getFilter().setColorLevels(range(percentage, 1, 50));
            }
        }

        private class GPU3x3TextureAdjuster extends Adjuster<GPUImage3x3TextureSamplingFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setLineSize(range(percentage, 0.0f, 5.0f));
            }
        }

        private class SaturationAdjuster extends Adjuster<GPUImageSaturationFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setSaturation(range(percentage, 0.0f, 2.0f));
            }
        }
        
        private class ExposureAdjuster extends Adjuster<GPUImageExposureFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setExposure(range(percentage, -10.0f, 10.0f));
            }
        }   
        
        private class HighlightShadowAdjuster extends Adjuster<GPUImageHighlightShadowFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setShadows(range(percentage, 0.0f, 1.0f));
                getFilter().setHighlights(range(percentage, 0.0f, 1.0f));
            }
        }
        
        private class MonochromeAdjuster extends Adjuster<GPUImageMonochromeFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setIntensity(range(percentage, 0.0f, 1.0f));
                //getFilter().setColor(new float[]{0.6f, 0.45f, 0.3f, 1.0f});
            }
        }
        
        private class OpacityAdjuster extends Adjuster<GPUImageOpacityFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setOpacity(range(percentage, 0.0f, 1.0f));
            }
        }   
        
        private class RGBAdjuster extends Adjuster<GPUImageRGBFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setRed(range(percentage, 0.0f, 1.0f));
                //getFilter().setGreen(range(percentage, 0.0f, 1.0f));
                //getFilter().setBlue(range(percentage, 0.0f, 1.0f));
            }
        }   
        
        private class WhiteBalanceAdjuster extends Adjuster<GPUImageWhiteBalanceFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setTemperature(range(percentage, 2000.0f, 8000.0f));
                //getFilter().setTint(range(percentage, -100.0f, 100.0f));
            }
        }

        private class VignetteAdjuster extends Adjuster<GPUImageVignetteFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setVignetteStart(range(percentage, 0.0f, 1.0f));
            }
        }

        private class DissolveBlendAdjuster extends Adjuster<GPUImageDissolveBlendFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setMix(range(percentage, 0.0f, 1.0f));
            }
        }

        private class GaussianBlurAdjuster extends Adjuster<GPUImageGaussianBlurFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setBlurSize(range(percentage, 0.0f, 1.0f));
            }
        }

        private class CrosshatchBlurAdjuster extends Adjuster<GPUImageCrosshatchFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setCrossHatchSpacing(range(percentage, 0.0f, 0.06f));
                getFilter().setLineWidth(range(percentage, 0.0f, 0.006f));
            }
        }

        private class BulgeDistortionAdjuster extends Adjuster<GPUImageBulgeDistortionFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setRadius(range(percentage, 0.0f, 1.0f));
                getFilter().setScale(range(percentage, -1.0f, 1.0f));
            }
        }

        private class GlassSphereAdjuster extends Adjuster<GPUImageGlassSphereFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setRadius(range(percentage, 0.0f, 1.0f));
            }
        }

        private class HazeAdjuster extends Adjuster<GPUImageHazeFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setDistance(range(percentage, -0.3f, 0.3f));
                getFilter().setSlope(range(percentage, -0.3f, 0.3f));
            }
        }

        private class SphereRefractionAdjuster extends Adjuster<GPUImageSphereRefractionFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setRadius(range(percentage, 0.0f, 1.0f));
            }
        }

        private class SwirlAdjuster extends Adjuster<GPUImageSwirlFilter> {
            @Override
            public void adjust(final int percentage) {
                getFilter().setAngle(range(percentage, 0.0f, 2.0f));
            }
        }

        private class ColorBalanceAdjuster extends Adjuster<GPUImageColorBalanceFilter> {

            @Override
            public void adjust(int percentage) {
                getFilter().setMidtones(new float[]{
                        range(percentage, 0.0f, 1.0f),
                        range(percentage / 2, 0.0f, 1.0f),
                        range(percentage / 3, 0.0f, 1.0f)});
            }
        }

        private class LevelsMinMidAdjuster extends Adjuster<GPUImageLevelsFilter> {
            @Override
            public void adjust(int percentage) {
                getFilter().setMin(0.0f, range(percentage, 0.0f, 1.0f) , 1.0f);
            }
        }

      

    }
}
