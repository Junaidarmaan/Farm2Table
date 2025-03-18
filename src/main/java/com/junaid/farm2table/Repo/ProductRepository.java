
package com.junaid.farm2table.Repo;

import com.junaid.farm2table.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}

