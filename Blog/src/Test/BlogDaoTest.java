package Test;

import Bean.Blog;
import Bean.Utilisateur;
import Dao.HSQLdb.BlogDao;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BlogDaoTest {

    public Blog monBlog = new Blog();
    public BlogDao monBlogDao = new BlogDao();

    @Test
    void testGetBlogNominal() {
        monBlog = monBlogDao.getBlog(1);
        assertEquals(monBlog.getTitre(), "titre1");
        assertEquals(monBlog.getDescription(), "contenue1");
    }

    @Test
    void testGetBlogNull() {
        monBlog = monBlogDao.getBlog(null);
        assertEquals(monBlog.getTitre(), null);
        assertEquals(monBlog.getDescription(), null);
    }

    @Test
    void testGetBlogMauvais() {
        monBlog = monBlogDao.getBlog(94568214);
        assertEquals(monBlog.getTitre(), null);
        assertEquals(monBlog.getDescription(), null);
    }

    @Test
    void testADDBlog() throws SQLException {
        Utilisateur monUser= new Utilisateur("contact@aquasys.fr", "contact", "pass", new Date(2019,05,17), true);
        Blog myBlog = new Blog(9999,"titre","madescription",monUser, new Date(2019,05,17), new  Date(2019,05,17));

        monBlogDao.createBlog(myBlog);

        monBlog = monBlogDao.getBlog(9999);
        assertEquals(monBlog.getTitre(), "titre");
        assertEquals(monBlog.getDescription(), "madescription");
    }
}