package web.app.api;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @Test
    public void testGetStudents(){
        String endpoint = "http://localhost:8080/students";
        given().when().get(endpoint).then().statusCode(200);
    }

    @Test
    public void testGetStudentWithId(){
        String endpoint = "http://localhost:8080/students";
        given().
                queryParam("id",1).
        when().
                get(endpoint).
        then().
                assertThat().
                    statusCode(200).
                    body("id",equalTo(1)).
                    body("firstName",equalTo("James")).
                    body("lastName",equalTo("Smith")).
                    body("email",equalTo("james_smith@anywhere.school"));

    }

    @Test
    public void testCreateStudent(){
        String endpoint = "http://localhost:8080/students";
        Student body = new Student("Sergio","Teacher","sergio_teacher@anywhere.school");
        given().
                header("Content-Type","application/json").
                body(body).
        when().
                post(endpoint).
        then().
                assertThat().
                statusCode(200).
                body("firstName",equalTo("Sergio")).
                body("lastName",equalTo("Teacher")).
                body("email",equalTo("sergio_teacher@anywhere.school"));
    }

    @Test
    public void testUpdateStudent(){
        String endpoint = "http://localhost:8080/students/{id}";
        int id = 1;
        Student body = new Student("John","Truck","john_truck@anywhere.school");
        given().
                header("Content-Type","application/json").
                body(body).
        when().
                put(endpoint,id).
        then().
               assertThat().
                statusCode(200).
                body("firstName",equalTo("John")).
                body("lastName",equalTo("Truck")).
                body("email",equalTo("john_truck@anywhere.school"));
    }

    @Test
    public void testDeleteStudent(){
        String endpoint = "http://localhost:8080/students/{id}";
        int id = 2;
        given().
        when().
                delete(endpoint,id).
        then().
                assertThat().
                    statusCode(200).
                    body("id", equalTo(id));
    }





}
