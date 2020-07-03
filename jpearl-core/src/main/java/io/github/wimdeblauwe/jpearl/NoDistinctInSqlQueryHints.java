package io.github.wimdeblauwe.jpearl;

import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is a {@link QueryHints} meta-annotation. It sets the 'hibernate.query.passDistinctThrough' to
 * {@code false}.
 * <p>
 * See https://vladmihalcea.com/jpql-distinct-jpa-hibernate/ for more info why you might want to use this.
 * <p>
 * Example usage:
 * <pre>
 * &#64;NoDistinctInSqlQueryHints
 * &#64;Query("SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.title = :title")
 * List&#60;Post&#62; findPostsWithCommentsByTitle(&#64;QueryParam("title") String title);
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@QueryHints(@QueryHint(name = "hibernate.query.passDistinctThrough", value = "false"))
public @interface NoDistinctInSqlQueryHints {

}
