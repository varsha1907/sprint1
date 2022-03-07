package com.bobocode.dao;

import com.bobocode.model.Photo;
import com.bobocode.model.PhotoComment;
import com.bobocode.util.EntityManagerUtil;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Objects;

/**
 * Please note that you should not use auto-commit mode for your implementation.
 */
public class PhotoDaoImpl implements PhotoDao {
   // private EntityManagerFactory entityManagerFactory;
    private EntityManagerUtil emUtil;

    public PhotoDaoImpl(EntityManagerFactory entityManagerFactory) {
      //  this.entityManagerFactory = entityManagerFactory;
        this.emUtil = new EntityManagerUtil(entityManagerFactory);
    }

    @Override
    public void save(Photo photo) {
       // throw new UnsupportedOperationException("Just do it!"); // todo
        Objects.requireNonNull(photo);
        emUtil.performWithinTx(entityManager -> entityManager.persist(photo));
    }

    @Override
    public Photo findById(long id) {
        //throw new UnsupportedOperationException("Just do it!"); // todo
        return emUtil.performReturningWithinTx(entityManager -> entityManager.find(Photo.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Photo> findAll() {
       // throw new UnsupportedOperationException("Just do it!"); // todo
        return emUtil.performReturningWithinTx(
                entityManager -> entityManager.createQuery("select p from Photo p").getResultList()
        );
    }

    @Override
    public void remove(Photo photo) {
       // throw new UnsupportedOperationException("Just do it!"); // todo
        Objects.requireNonNull(photo);
        emUtil.performWithinTx(entityManager -> {
            Photo managedPhoto = entityManager.merge(photo);
            entityManager.remove(managedPhoto);
        });
    }
    
    @Override
    public void addComment(long photoId, String comment) {
      //  throw new UnsupportedOperationException("Just do it!"); // todo
        emUtil.performWithinTx(entityManager -> {
            Photo photoReference = entityManager.getReference(Photo.class, photoId);
            PhotoComment photoComment = new PhotoComment(comment, photoReference);
            entityManager.persist(photoComment);
        });
    }
}
