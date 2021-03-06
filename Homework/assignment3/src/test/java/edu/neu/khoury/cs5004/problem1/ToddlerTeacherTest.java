package edu.neu.khoury.cs5004.problem1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ToddlerTeacherTest {

  private ToddlerTeacher t;

  @Before
  public void setUp() throws Exception {
    t = new ToddlerTeacher(new Name("Evan", "Douglass"),
        "306",
        new Name("John", "Johns"),
        false,
        2,
        7,
        1,
        1
    );
  }

  @Test
  public void makeFullTimeToddler() {
    SubstituteTeacher sub = new SubstituteTeacher(new Name("Marge", "Simpson"),
        "307",
        new Name("", ""),
        false,
        5);
    ToddlerTeacher newFull = (ToddlerTeacher) t.convertSubstituteTeacher(sub, "Toddler", t);

    // test name
    assertEquals(sub.getName(), newFull.getName());
    // test classroom
    assertEquals(t.getClassroom(), newFull.getClassroom());
    // test coTeachers
    assertEquals(t.getName(), newFull.getCoTeacher());
    assertEquals(newFull.getCoTeacher(), t.getName());
    // test vacay
    assertFalse(newFull.getVacationStatus());
    // test num children
    assertEquals(t.getCurrNumChildren(), newFull.getCurrNumChildren());
    // test max
    assertEquals(14, (int) t.getMaxChildren());
    assertEquals(14, (int) newFull.getMaxChildren());
    // test crawlers
    assertEquals(t.getCurrNumInDiapers(), newFull.getCurrNumInDiapers());
    // test walkers
    assertEquals(t.getCurrNumPottyTrained(), newFull.getCurrNumPottyTrained());
  }

  @Test
  public void convertToToddlerAndChangeMax() {
    t = new ToddlerTeacher(new Name("Evan", "Douglass"),
        "306",
        new Name("", ""),
        false,
        2,
        7,
        1,
        1
    );

    SubstituteTeacher sub = new SubstituteTeacher(new Name("Marge", "Simpson"),
        "307",
        new Name("", ""),
        false,
        5);
    ToddlerTeacher newFull = (ToddlerTeacher) t.convertSubstituteTeacher(sub, "Toddler", t);

    assertEquals(14, (int) t.getMaxChildren());
    assertEquals(14, (int) newFull.getMaxChildren());
  }

  @Test
  public void changeCoTeacher() {
    t.setCoTeacher(new Name("", ""));
    assertEquals(7, (int) t.getMaxChildren());
    t.setCoTeacher(new Name("Hello", "There"));
    assertEquals(14, (int) t.getMaxChildren());
  }

  @Test
  public void toString1() {
    String result = "ToddlerTeacher: name=Evan Douglass classroom=306 coTeacher=John Johns "
        + "onVacation=false numChildren=2 maxChildren=7 childrenInDiapers=1 childrenPottyTrained=1";
    assertEquals(result, t.toString());
  }

  @Test
  public void equals1() {
    ToddlerTeacher t2 = new ToddlerTeacher(new Name("Evan", "Douglass"),
        "306",
        new Name("John", "Johns"),
        false,
        2,
        7,
        1,
        1
    );
    assertEquals(t2, t);

    t2.setCurrNumInDiapers(0);
    assertNotEquals(t2, t);
  }

  @Test
  public void hashCode1() {
    ToddlerTeacher t2 = new ToddlerTeacher(new Name("Evan", "Douglass"),
        "306",
        new Name("John", "Johns"),
        false,
        2,
        7,
        1,
        1
    );
    assertEquals(t2.hashCode(), t.hashCode());

    t2.setCurrNumPottyTrained(0);
    assertNotEquals(t2.hashCode(), t.hashCode());
  }

  @Test
  public void getAndSetCurrNumInDiapers() {
    assertEquals(1, (int) t.getCurrNumInDiapers());
    t.setCurrNumInDiapers(0);
    assertEquals(0, (int) t.getCurrNumInDiapers());

    try {
      t.setCurrNumInDiapers(4);
      fail("there can't be more children in diapers than there are in the class");
    } catch (IllegalArgumentException e) {
      // Passed
    }

    try {
      t.setCurrNumInDiapers(-1);
      fail("there can't be a negative number of children in diapers");
    } catch (IllegalArgumentException e) {
      // Passed
    }
  }

  @Test
  public void getAndSetCurrNumPottyTrained() {
    assertEquals(1, (int) t.getCurrNumPottyTrained());
    t.setCurrNumPottyTrained(0);
    assertEquals(0, (int) t.getCurrNumPottyTrained());

    try {
      t.setCurrNumPottyTrained(4);
      fail("there can't be more children in potty trained than there are in the class");
    } catch (IllegalArgumentException e) {
      // Passed
    }

    try {
      t.setCurrNumPottyTrained(-1);
      fail("there can't be a negative number of children in diapers");
    } catch (IllegalArgumentException e) {
      // Passed
    }
  }
}