/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.fop.render.shading;

import java.util.ArrayList;
import java.util.List;

import org.apache.fop.pdf.PDFDeviceColorSpace;
import org.apache.fop.pdf.PDFFunction;
import org.apache.fop.pdf.PDFPattern;
import org.apache.fop.pdf.PDFShading;
import org.apache.fop.svg.PDFGraphics2D;

public class PDFGradientFactory extends GradientFactory<PDFPattern> {

    private final PDFGraphics2D graphics2D;

    public PDFGradientFactory(PDFGraphics2D pdfGraphics2D) {
        this.graphics2D = pdfGraphics2D;
    }

    @Override
    public Shading makeShading(int theShadingType,
            PDFDeviceColorSpace theColorSpace, List<Double> theBackground, List<Double> theBBox,
            boolean theAntiAlias, List<Double> theCoords, List<Double> theDomain,
            Function theFunction, List<Integer> theExtend) {
        List<PDFFunction> pdfFunctions = new ArrayList<PDFFunction>(theFunction.getFunctions().size());
        for (Function f : theFunction.getFunctions()) {
            pdfFunctions.add(graphics2D.registerFunction(new PDFFunction(f)));
        }
        PDFFunction pdfFunction = graphics2D.registerFunction(new PDFFunction(theFunction, pdfFunctions));
        PDFShading newShading = new PDFShading(theShadingType, theColorSpace, theBackground,
                    theBBox, theAntiAlias, theCoords, theDomain, pdfFunction, theExtend);
        newShading = graphics2D.registerShading(newShading);
        return newShading;
    }

    @Override
    public PDFPattern makePattern(int thePatternType, Shading theShading, List theXUID,
            StringBuffer theExtGState, List<Double> theMatrix) {
        PDFPattern newPattern = new PDFPattern(thePatternType, theShading, theXUID, theExtGState,
                    theMatrix);
        newPattern = graphics2D.registerPattern(newPattern);
        return newPattern;
    }

}