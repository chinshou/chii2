package org.chii2.medialibrary.persistence;

import org.chii2.medialibrary.api.persistence.PersistenceService;
import org.chii2.medialibrary.api.persistence.entity.Movie;
import org.chii2.medialibrary.api.persistence.entity.MovieFile;
import org.chii2.medialibrary.api.persistence.entity.MovieImage;
import org.chii2.medialibrary.api.persistence.entity.MovieInfo;
import org.chii2.medialibrary.persistence.entity.MovieFileImpl;
import org.chii2.medialibrary.persistence.entity.MovieImageImpl;
import org.chii2.medialibrary.persistence.entity.MovieImpl;
import org.chii2.medialibrary.persistence.entity.MovieInfoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Persistence layer
 */
public class PersistenceServiceImpl implements PersistenceService {

    // Entity Manager
    private EntityManager entityManager;
    // Logger
    private Logger logger;

    public PersistenceServiceImpl() {
        logger = LoggerFactory.getLogger("org.chii2.medialibrary.persistence");
    }

    /**
     * Life Cycle Init
     */
    @SuppressWarnings("unused")
    public void init() {
        logger.debug("Chii2 Media Library PersistenceService init.");
    }

    /**
     * Life Cycle Destroy
     */
    @SuppressWarnings("unused")
    public void destroy() {
        logger.debug("Chii2 Media Library PersistenceService destroy.");
    }

    @Override
    public List<MovieImpl> getAllMovies() {
        // Get the current movie files list from database
        return entityManager.createNamedQuery("Movie.findAll", MovieImpl.class).getResultList();
    }

    @Override
    public Movie getMovieById(String id) {
        // Get movie by id, should be a only one result
        try {
            return entityManager.createNamedQuery("Movie.findById", MovieImpl.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Try to get movie <{}> but not exist.", id);
            return null;
        }
    }

    @Override
    public List<? extends Movie> getAllMoviesByName(String movieName) {
        // Get movie by name, return all possible movies
        return entityManager.createNamedQuery("Movie.findByName", MovieImpl.class).setParameter("name", "%" + movieName.toLowerCase() + "%").getResultList();
    }

    @Override
    public Movie getSingleMovieByName(String movieName) {
        // Get movie by name, return single result
        try {
            return entityManager.createNamedQuery("Movie.findByName", MovieImpl.class).setParameter("name", "%" + movieName.toLowerCase() + "%").getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Try to get movie with name <{}> but not exist.", movieName);
            return null;
        }
    }

    @Override
    public MovieFile getMovieFileById(String id) {
        // Get movie by id, should be a only one result
        try {
            return entityManager.createNamedQuery("MovieFile.findById", MovieFileImpl.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Try to get movie <{}> but not exist.", id);
            return null;
        }
    }

    @Override
    public MovieInfo getMovieInfoById(String id) {
        // Get movie by id, should be a only one result
        try {
            return entityManager.createNamedQuery("MovieInfo.findById", MovieInfoImpl.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Try to get movie <{}> but not exist.", id);
            return null;
        }
    }

    @Override
    public MovieImage getMovieImageById(String imageId) {
        // Get movie image by id, should be a only one result
        try {
            return entityManager.createNamedQuery("MovieImage.findById", MovieImageImpl.class).setParameter("id", imageId).getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Try to get movie image <{}> but not exist.", imageId);
            return null;
        }
    }

    @Override
    public void persist(List<Movie> movies) {
        for (Movie movie : movies) {
            if (movie.getClass() == MovieImpl.class) {
                entityManager.persist(movie);
            }
        }
    }

    @Override
    public void persist(Movie movie) {
        if (movie.getClass() == MovieImpl.class) {
            entityManager.persist(movie);
        }
    }

    @Override
    public void persist(MovieFile movieFile) {
        if (movieFile.getClass() == MovieFileImpl.class) {
            entityManager.persist(movieFile);
        }
    }

    @Override
    public void persist(MovieInfo movieInfo) {
        if (movieInfo.getClass() == MovieInfoImpl.class) {
            entityManager.persist(movieInfo);
        }
    }

    @Override
    public void persist(MovieImage movieImage) {
        if (movieImage.getClass() == MovieImageImpl.class) {
            entityManager.persist(movieImage);
        }
    }

    @Override
    public void merge(Movie movie) {
        if (movie.getClass() == MovieImpl.class) {
            entityManager.merge(movie);
        }
    }

    @Override
    public void merge(MovieFile movieFile) {
        if (movieFile.getClass() == MovieFileImpl.class) {
            entityManager.merge(movieFile);
        }
    }

    @Override
    public void merge(MovieInfo movieInfo) {
        if (movieInfo.getClass() == MovieInfoImpl.class) {
            entityManager.merge(movieInfo);
        }
    }

    @Override
    public void merge(MovieImage movieImage) {
        if (movieImage.getClass() == MovieImageImpl.class) {
            entityManager.merge(movieImage);
        }
    }

    @Override
    public void remove(Movie movie) {
        if (movie.getClass() == MovieImpl.class) {
            entityManager.remove(movie);
        }
    }

    @Override
    public void remove(MovieFile movieFile) {
        if (movieFile.getClass() == MovieFileImpl.class) {
            entityManager.remove(movieFile);
        }
    }

    @Override
    public void remove(MovieInfo movieInfo) {
        if (movieInfo.getClass() == MovieInfoImpl.class) {
            entityManager.remove(movieInfo);
        }
    }

    @Override
    public void remove(MovieImage movieImage) {
        if (movieImage.getClass() == MovieImageImpl.class) {
            entityManager.remove(movieImage);
        }
    }

    /**
     * Inject Entity Manager
     *
     * @param entityManager Entity Manager
     */
    @SuppressWarnings("unused")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}