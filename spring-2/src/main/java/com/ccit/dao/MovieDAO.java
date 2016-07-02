package com.ccit.dao;

import com.ccit.interface_dao.MovieDao;
import com.ccit.pojo.Movie;
import org.springframework.jdbc.core.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Named
public class MovieDAO implements MovieDao {
    @Inject
    private JdbcTemplate jdbcTemplate;
    public void delete(Integer id) {
        String sql = "delete from movie where id = ? ";
        jdbcTemplate.update(sql,id);
    }

    public void update(Movie movie) {
        String sql = "update movie set title=?,rate=?,sendtime=?,daoyan=? where id=?";
        jdbcTemplate.update(sql,movie.getTitle(),movie.getRate(),
                movie.getSendtime(),movie.getDaoyan(),movie.getId());
    }

    public void insert(Movie movie) {
       String sql = "insert into movie(title,rate,sendtime,daoyan) values(?,?,?,?)";
        jdbcTemplate.update(sql,movie.getTitle(),movie.getRate(),movie.getSendtime(),movie.getDaoyan());
    }

    public Movie findById(Integer id) {
        String sql = "select * from movie where id = ?";
        return  jdbcTemplate.queryForObject(sql, new RowMapper<Movie>() {
            public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
                Movie movie = new Movie();
                movie.setTitle(resultSet.getString("title"));
                movie.setRate(resultSet.getFloat("rate"));
                movie.setSendtime(resultSet.getString("sendtime"));
                movie.setDaoyan(resultSet.getString("daoyan"));
                return movie;
            }
        }, id);
    }

    public List<Movie> findAll() {
        String sql = "select * from movie";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Movie>(Movie.class));
    }

    public Movie findByTitle(String title) {
        String sql = "select * from movie where title = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Movie>(Movie.class),title);
    }
}
