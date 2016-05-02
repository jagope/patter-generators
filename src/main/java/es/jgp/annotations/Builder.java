package es.jgp.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Añadir esta anotación a las clases sobre las que queremos aplicar el patrón builder
 *
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Builder {
}
