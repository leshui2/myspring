package com.example.demo.service;

import com.example.demo.entity.Student;

import com.example.demo.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取用户策略：先从缓存中获取用户，没有则取数据表中 数据，再将数据写入缓存
     */
    public Student selectByPrimaryKey(Integer stuId) {
        String key = "student_" + stuId;
        ValueOperations<String, Student> operations = redisTemplate.opsForValue();
        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            Student student = operations.get(key);
            log.info("从缓存中获取数据:{}", student.getStuName());
            return student;
        } else {
            Student student = studentMapper.selectByPrimaryKey(stuId);
            log.info("从数据库中查询{}", student.getStuName());
            //写入缓存
            operations.set(key, student, 5, TimeUnit.HOURS);
            return student;
        }

    }



    public Student selectById(Integer stId) {
        String key = "student_" + stId;
        ValueOperations<String, Student> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            log.info("从缓存中读取:{}", key);
            Student student = valueOperations.get(key);
            return student;
        } else {
            Student student = studentMapper.selectByPrimaryKey(stId);
            valueOperations.set(key, student, 1, TimeUnit.HOURS);
            return student;
        }
    }

    /**
     * 更新用户策略：先更新数据表，成功之后，删除原来的缓存，再更新缓存
     */
    public int updateStudent(Student student) {
        ValueOperations<String, Student> operations = redisTemplate.opsForValue();
        int result = studentMapper.updateByPrimaryKey(student);
        if (result != 0) {
            String key = "student_" + student.getStuId();
            boolean haskey = redisTemplate.hasKey(key);
            if (haskey) {
                redisTemplate.delete(key);
                log.info("删除缓存中的key-----------> {}", key);
            }
            // 再将更新后的数据加入缓存
            Student studentNew = studentMapper.selectByPrimaryKey(student.getStuId());
            if (studentNew != null) {
                operations.set(key, studentNew, 3, TimeUnit.HOURS);
            }
        }
        return result;
    }

    /**
     * 删除用户策略：删除数据表中数据，然后删除缓存
     */
    public int deleteStudentById(int id) {
        int result = studentMapper.deleteByPrimaryKey(id);
        String key = "student_" + id;
        if (result != 0) {
            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                redisTemplate.delete(key);
                log.info("删除了缓存中的key:{}", key);
            }
        }
        return result;
    }

    public List<Student> listData() {
        List<Student> student =studentMapper.listData();
        return student;
    }
}
