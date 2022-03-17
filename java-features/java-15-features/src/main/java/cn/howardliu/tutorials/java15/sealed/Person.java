package cn.howardliu.tutorials.java15.sealed;

/***
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/3/3 08:28
 */
public sealed class Person permits Student, Worker, Teacher {
}
