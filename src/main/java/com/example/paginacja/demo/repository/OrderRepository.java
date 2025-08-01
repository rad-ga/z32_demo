package com.example.paginacja.demo.repository;

import com.example.paginacja.demo.model.Order;
import com.example.paginacja.demo.model.dto.OrderProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(
            value = """
      SELECT 
        o.id           AS id,
        o.customer     AS customer,
        COUNT(i.id)    AS orderCount
      FROM orders o
      LEFT JOIN order_items i
        ON i.order_id = o.id
      GROUP BY 
        o.id,
        o.customer
    """,
            countQuery = "SELECT COUNT(*) FROM orders",
            nativeQuery = true
    )
    Page<OrderProjection> findAllByProjection(Pageable pageable);

    @Query(
            value = """
                      SELECT DISTINCT o
                      FROM Order o
                      LEFT JOIN FETCH o.items
                    """,
            countQuery = "SELECT COUNT(o) FROM Order o"
    )
    Page<Order> findAllWithItems(Pageable pageable);  //HHH90003004: firstResult/maxResults specified with collection fetch; applying in memory


}

/*
 @Query(
      value = """
        select distinct o
          from Order o
     left join fetch o.items
         where (:customer   is null or o.customer = :customer)
           and (:minItems   is null or size(o.items) >= :minItems)
           and (:maxItems   is null or size(o.items) <= :maxItems)
      """,
      countQuery = """
        select count(distinct o)
          from Order o
         where (:customer   is null or o.customer = :customer)
           and (:minItems   is null or size(o.items) >= :minItems)
           and (:maxItems   is null or size(o.items) <= :maxItems)
      """
    )
    Page<Order> findByFilters(
      @Param("customer") String   customer,
      @Param("minItems") Integer  minItems,
      @Param("maxItems") Integer  maxItems,
      Pageable                   pageable
    );
 */