/*******************************************************************************
 * JetUML - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2022 by McGill University.
 *     
 * See: https://github.com/prmr/JetUML
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 *******************************************************************************/
package ca.mcgill.jetuml.layouttests;

import java.io.IOException;
import java.nio.file.Path;

import ca.mcgill.cs.jetuml.diagram.Node;
import ca.mcgill.cs.jetuml.diagram.nodes.ActorNode;
import ca.mcgill.cs.jetuml.geom.Dimension;
import ca.mcgill.cs.jetuml.viewers.StringViewer;
import ca.mcgill.cs.jetuml.viewers.StringViewer.Alignment;
import ca.mcgill.cs.jetuml.viewers.StringViewer.TextDecoration;
import ca.mcgill.cs.jetuml.viewers.nodes.ActorNodeViewer;
import ca.mcgill.cs.jetuml.viewers.nodes.UseCaseNodeViewer;

/**
 * Superclass for classes that test the layout of a use case diagram.
 * Declares convenience methods to test diagram elements. 
 */
public abstract class AbstractTestUseCaseDiagramLayout extends AbstractTestDiagramLayout 
{

	AbstractTestUseCaseDiagramLayout(Path pDiagramPath) throws IOException 
	{
		super(pDiagramPath);
	}
	
	protected static void verifyUseCaseNodeDefaultDimensions(Node pNode)
	{
		final int DEFAULT_WIDTH = getStaticIntFieldValue(UseCaseNodeViewer.class, "DEFAULT_WIDTH");
		final int DEFAULT_HEIGHT = getStaticIntFieldValue(UseCaseNodeViewer.class, "DEFAULT_HEIGHT");
		verifyDefaultDimensions(pNode, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	protected static void verifyActorNodeWithNameDefaultDimensions(Node pNode)
	{
		final int WIDTH = getStaticIntFieldValue(ActorNodeViewer.class, "WIDTH");
		final int HEIGHT = getStaticIntFieldValue(ActorNodeViewer.class, "HEIGHT");
		StringViewer nameViewer = StringViewer.get(Alignment.CENTER_CENTER, TextDecoration.PADDED);
		Dimension nameBounds = nameViewer.getDimension(((ActorNode)pNode).getName());
		int calculatedWidth = Math.max(WIDTH, nameBounds.width());
		int calculatedHeight = HEIGHT + nameBounds.height();
		verifyDefaultDimensions(pNode, calculatedWidth, calculatedHeight);
	}
}
