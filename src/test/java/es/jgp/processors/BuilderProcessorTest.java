package es.jgp.processors;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.testing.compile.JavaFileObjects;

/**
 * Tests the {@link BuilderProcessor}.
 */
@RunWith(JUnit4.class)
public class BuilderProcessorTest {
  @Test
  public void autoService() {
    assert_().about(javaSources())
        .that(Arrays.asList(
            JavaFileObjects.forResource("test/SomeBuilder.java")
//            ,JavaFileObjects.forResource("test/SomeServiceProvider1.java")
//            ,JavaFileObjects.forResource("test/SomeServiceProvider2.java")
//            ,JavaFileObjects.forResource("test/Enclosing.java")
//            ,JavaFileObjects.forResource("test/AnotherService.java")
//            ,JavaFileObjects.forResource("test/AnotherServiceProvider.java")
         ))
        .processedWith(new BuilderProcessor())
        .compilesWithoutError()
        .and().generatesFiles(
            JavaFileObjects.forResource("META-INF/services/test.SomeService")
//            ,JavaFileObjects.forResource("META-INF/services/test.AnotherService")
            );
  }
}