package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {

    private final BasicDataSource pool = new BasicDataSource();

    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM dreamjob.public.post")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        var candidates = new ArrayList<Candidate>();

        try (var cn = pool.getConnection();
             var ps = cn.prepareStatement("SELECT * FROM  dreamjob.public.candidate")
        ) {
           try (var rs = ps.executeQuery()) {
               while (rs.next()) {
                   candidates.add(new Candidate(rs.getInt("id"), rs.getString("name")));
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return candidates;
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            create(post);
        } else {
            update(post);
        }
    }

    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            create(candidate);
        } else {
            update(candidate);
        }
    }

    private Post create(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO dreamjob.public.post(name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    private Candidate create(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO dreamjob.public.candidate(name) " +
                     "VALUES (?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }

    private void update(Post post) {
        try (var cn = pool.getConnection();
             var ps =  cn.prepareStatement(
                     "UPDATE dreamjob.public.post " +
                     "SET name = ? " +
                     "WHERE id = ?"
             )
        ) {
            ps.setString(1, post.getName());
            ps.setInt(2, post.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(Candidate candidate) {
        try (var cn = pool.getConnection();
             var ps =  cn.prepareStatement(
                     "UPDATE dreamjob.public.candidate " +
                     "SET name = ? " +
                     "WHERE id = ?"
             )
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (var cn = pool.getConnection();
             var ps =  cn.prepareStatement(
                     "SELECT * FROM dreamjob.public.post " +
                             "WHERE id = ?"
             )
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    var name = rs.getString("name");
                    post = new Post(id, name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return post;
    }

    public Candidate findCandidateById(int id) {
        Candidate candidate = null;
        try (var cn = pool.getConnection();
             var ps =  cn.prepareStatement(
                     "SELECT * FROM dreamjob.public.candidate " +
                             "WHERE id = ?"
             )
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    var name = rs.getString("name");
                    candidate = new Candidate(id, name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }

    @Override
    public void removeCandidate(int id) {
        Candidate candidate = null;
        try (var cn = pool.getConnection();
             var ps =  cn.prepareStatement(
                     "DELETE  FROM dreamjob.public.candidate " +
                             "WHERE id = ?"
             )
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0) {
            create(user);
        } else {
            update(user);
        }
    }

    private User create(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO dreamjob.public.users(email, name, password) " +
                             "VALUES (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    private void update(User user) {
        try (var cn = pool.getConnection();
             var ps =  cn.prepareStatement(
                     "UPDATE dreamjob.public.users " +
                             "SET name = ?," +
                             "email = ?, " +
                             "password = ? " +
                             "WHERE id = ?"
             )
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByEmail(String email) {
        User user = null;
        try (var cn = pool.getConnection();
             var ps =  cn.prepareStatement(
                     "SELECT * FROM dreamjob.public.users " +
                             "WHERE email = ?"
             )
        ) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(email);
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void removeUser(int id) {
        User user = null;
        try (var cn = pool.getConnection();
             var ps =  cn.prepareStatement(
                     "DELETE  FROM dreamjob.public.users " +
                             "WHERE id = ?"
             )
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}