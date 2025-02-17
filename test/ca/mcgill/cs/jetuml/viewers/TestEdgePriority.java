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
package ca.mcgill.cs.jetuml.viewers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcgill.cs.jetuml.diagram.Diagram;
import ca.mcgill.cs.jetuml.diagram.DiagramType;
import ca.mcgill.cs.jetuml.diagram.Edge;
import ca.mcgill.cs.jetuml.diagram.Node;
import ca.mcgill.cs.jetuml.diagram.edges.AggregationEdge;
import ca.mcgill.cs.jetuml.diagram.edges.AssociationEdge;
import ca.mcgill.cs.jetuml.diagram.edges.DependencyEdge;
import ca.mcgill.cs.jetuml.diagram.edges.GeneralizationEdge;
import ca.mcgill.cs.jetuml.diagram.edges.GeneralizationEdge.Type;
import ca.mcgill.cs.jetuml.diagram.edges.NoteEdge;
import ca.mcgill.cs.jetuml.diagram.nodes.ClassNode;

public class TestEdgePriority 
{
	private Diagram aDiagram = new Diagram(DiagramType.CLASS);
	private Edge aInheritanceEdge = new GeneralizationEdge(Type.Inheritance);
	private Edge aImplementationEdge = new GeneralizationEdge(Type.Implementation);
	private Edge aAggregationEdge = new AggregationEdge(AggregationEdge.Type.Aggregation);
	private Edge aCompositionEdge = new AggregationEdge(AggregationEdge.Type.Composition);
	private Edge aAssociationEdge = new AssociationEdge();
	private Edge aDependencyEdge = new DependencyEdge();
	private Edge aSelfEdge = new AggregationEdge(AggregationEdge.Type.Composition);
	private Edge aNoteEdge = new NoteEdge();
	private Node aNode1 = new ClassNode();

	@BeforeEach
	private void setUp()
	{
		aSelfEdge.connect(aNode1, aNode1, aDiagram);
	}
	
	@Test
	public void testPriorityOf_inheritance()
	{
		assertEquals(EdgePriority.priorityOf(aInheritanceEdge), EdgePriority.INHERITANCE);
	}
	
	@Test
	public void testPriorityOf_implementation()
	{
		assertEquals(EdgePriority.priorityOf(aImplementationEdge), EdgePriority.IMPLEMENTATION);
	}
	
	@Test
	public void testPriorityOf_aggregation()
	{
		assertEquals(EdgePriority.priorityOf(aAggregationEdge), EdgePriority.AGGREGATION);
	}
	
	@Test
	public void testPriorityOf_composition()
	{
		assertEquals(EdgePriority.priorityOf(aCompositionEdge), EdgePriority.COMPOSITION);
	}
	
	@Test
	public void testPriorityOf_association()
	{
		assertEquals(EdgePriority.priorityOf(aAssociationEdge), EdgePriority.ASSOCIATION);
	}
	
	@Test
	public void testPriorityOf_dependency()
	{
		assertEquals(EdgePriority.priorityOf(aDependencyEdge), EdgePriority.DEPENDENCY);
	}
	
	@Test
	public void testPriorityOf_selfEdge()
	{
		assertEquals(EdgePriority.priorityOf(aSelfEdge), EdgePriority.SELF_EDGE);
	}
	
	@Test
	public void testPriorityOf_other()
	{
		assertEquals(EdgePriority.priorityOf(aNoteEdge), EdgePriority.OTHER);
	}
	
	@Test
	public void testIsSegmented_givenPriority()
	{
		assertTrue(EdgePriority.isSegmented(EdgePriority.INHERITANCE));
		assertTrue(EdgePriority.isSegmented(EdgePriority.IMPLEMENTATION));
		assertTrue(EdgePriority.isSegmented(EdgePriority.AGGREGATION));
		assertTrue(EdgePriority.isSegmented(EdgePriority.COMPOSITION));
		assertTrue(EdgePriority.isSegmented(EdgePriority.ASSOCIATION));
		assertFalse(EdgePriority.isSegmented(EdgePriority.DEPENDENCY));
		assertFalse(EdgePriority.isSegmented(EdgePriority.OTHER));
		assertFalse(EdgePriority.isSegmented(EdgePriority.SELF_EDGE));
	}
	
	@Test
	public void testIsSegmented_givenEdge()
	{
		assertTrue(EdgePriority.isSegmented(aInheritanceEdge));
		assertTrue(EdgePriority.isSegmented(aImplementationEdge));
		assertTrue(EdgePriority.isSegmented(aAggregationEdge));
		assertTrue(EdgePriority.isSegmented(aCompositionEdge));
		assertTrue(EdgePriority.isSegmented(aAssociationEdge));
		assertFalse(EdgePriority.isSegmented(aDependencyEdge));
		assertFalse(EdgePriority.isSegmented(aSelfEdge));
		assertFalse(EdgePriority.isSegmented(aNoteEdge));
	}
	
	@Test
	public void testIsClassDiagramEdge()
	{
		assertTrue(EdgePriority.isStoredEdge(aInheritanceEdge));
		assertTrue(EdgePriority.isStoredEdge(aImplementationEdge));
		assertTrue(EdgePriority.isStoredEdge(aAggregationEdge));
		assertTrue(EdgePriority.isStoredEdge(aCompositionEdge));
		assertTrue(EdgePriority.isStoredEdge(aAssociationEdge));
		assertTrue(EdgePriority.isStoredEdge(aDependencyEdge));
		assertTrue(EdgePriority.isStoredEdge(aSelfEdge));
		assertFalse(EdgePriority.isStoredEdge(aNoteEdge));
	}
}