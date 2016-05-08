package es.jgp.processors;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;

import com.google.auto.service.AutoService;

import es.jgp.annotations.Builder;

@AutoService(Processor.class)
public class BuilderProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (Element element : roundEnv.getElementsAnnotatedWith(Builder.class)) {

			TypeElement typeElement = (TypeElement) element;
			String className = typeElement.getSimpleName().toString();
			String classQName = typeElement.getQualifiedName().toString();
			String packageName = ((PackageElement) typeElement.getEnclosingElement()).getQualifiedName().toString();

			PrintWriter w;
			try {
				w = new PrintWriter(processingEnv.getFiler().createSourceFile(classQName, element).openWriter());

				w.print("package " + packageName + ";");
				w.print("public class " + className + " { }");
				w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> supportedAnnotationTypes = new HashSet<String>(1);
		supportedAnnotationTypes.add(Builder.class.getName());
		return supportedAnnotationTypes;
	}

}
