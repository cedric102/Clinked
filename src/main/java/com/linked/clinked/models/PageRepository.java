package com.linked.clinked.models;

import java.util.List;

import com.linked.clinked.models.data.Page;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Integer> {

    // SELECT * FROM Page WHERE Slug = slug
    Page findBySlug(String slug);

    // @Query("SELECT p FROM Page p WHERE p.id != :id and p.slug = :slug")
    // Page findBySlug( int id , String slug );
    Page findBySlugAndIdNot(String slug, int id);

    List<Page> findAllByOrderBySortingAsc();

}
