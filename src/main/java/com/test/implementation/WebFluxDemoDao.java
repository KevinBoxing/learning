package com.test.implementation;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * Description:  <br>
 * Date: 2022-9-6 11:02<br>
 *
 * @author moon
 * @since 1.0.0
 */

public interface WebFluxDemoDao extends ReactiveCrudRepository<WebFluxDemoEntity,String> {
}
