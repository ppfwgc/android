package biz.webgate.sqs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface SQLiteStore {
	String DatabaseName();
	String TableName();
	String PrimaryKeyCol();
	String PrimaryKeyType();
	String Version();
	String JavaFieldPrefix() default "m_";
	String JavaFieldPostFix() default "";
}
