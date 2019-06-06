package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometrie.Point;
import geometrie.Segment;

class SegmentTest {

	@Test
	void testGetPoints() {
		
		Point p1, p2 ;
		Segment s1 ;
		
		p1 = new Point (1, 1) ;
		p2 = new Point (2, 2) ;
		s1 = new Segment(p1, p2) ;
		
		assertEquals(new Point(1, 1), s1.getPoint1()) ;
		assertEquals(new Point(2, 2), s1.getPoint2()) ;
		
	}
	
	@Test
	void testIntersection () {
		
		Point p0, p1, p2, p3 ;
		Segment s1, s2 ;
		
		p0 = new Point (1, 1) ;
		p1 = new Point (2, 2) ;
		p2 = new Point (1, 2) ;
		p3 = new Point (2, 1) ;
		
		s1 = new Segment (p0, p1) ;
		s2 = new Segment (p2, p3) ;
		
		assertEquals(new Point (1.5, 1.5), s1.intersection(s2)) ;
		
		p0 = new Point (1, 1) ;
		p1 = new Point (2, 2) ;
		p2 = new Point (3, 3) ;
		p3 = new Point (4, 4) ;
		
		s1 = new Segment (p0, p1) ;
		s2 = new Segment (p2, p3) ;
		
		assertEquals(null, s1.intersection(s2)) ;
		
	}

}
