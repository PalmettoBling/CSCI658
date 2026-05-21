// Introduction to Software Testing
// Authors: Paul Ammann & Jeff Offutt
// Chapter 1; page ??
// JUnit to exercise java.math.BigDecimal class

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.math.*;

public class BigDecimalTest { 
  private BigDecimal x;
  private BigDecimal y;

  Set <BigDecimal> tree;
  Set <BigDecimal> hash;

  @Before public void setUp() {
     x = new BigDecimal("1.0"); 
     y = new BigDecimal("1.00");
     // Fact:  !x.equals(y), but x.compareTo(y) == 0

     tree = new TreeSet <BigDecimal> ();
     hash = new HashSet <BigDecimal> ();
  }
 
  // this test fails!
  @Test public void inconsistentSets() {
     tree.add(x); tree.add(y);
     // TreeSet uses compareTo(), so tree now has 1 element

     hash.add(x); hash.add(y);
     // HashSet uses equals(), so hash now has 2 elements

     assertEquals(tree, hash);   // hence this assertion cannot possibly be true
  }
} 