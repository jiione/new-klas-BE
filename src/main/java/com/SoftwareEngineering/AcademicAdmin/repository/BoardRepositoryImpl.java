package com.SoftwareEngineering.AcademicAdmin.repository;

import com.SoftwareEngineering.AcademicAdmin.entity.Board;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> findLatestPostsByBoardCode(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> query = cb.createQuery(Post.class);
        Root<Board> boardRoot = query.from(Board.class);
        Join<Board, Post> postJoin = boardRoot.join("posts");

        query.select(postJoin)
                .where(cb.equal(boardRoot.get("code"), 0))
                .orderBy(cb.desc(postJoin.get("time")));

        TypedQuery<Post> typedQuery = entityManager.createQuery(query);
        typedQuery.setMaxResults(5);

        return typedQuery.getResultList();
    }

}
