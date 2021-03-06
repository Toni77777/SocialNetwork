package by.grodno.toni7777.socialnetwork.dagger2.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * This provide Scope to All Presenters 
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PresenterScope {
}
