import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import net.sf.json.JSONObject
import spock.lang.Shared
import spock.lang.Specification

class UsersTest extends Specification {

    @Shared
    def client = new RESTClient("https://reqres.in/")

    def "should return 200 for list of users"() {
        when:
        def response = client.get(path:"/api/users", query: [page:2])

        then: "server returns 200 response"
        response.status == 200

        then: "verify the response attributes"
        response.data.page == 2
        response.data.total == 12
        response.data.total_pages == 2
        response.data.data.size() == 6
        response.data.data == [
                new JSONObject(
                        "id": 7,
                        "email": "michael.lawson@reqres.in",
                        "first_name": "Michael",
                        "last_name": "Lawson",
                        "avatar": "https://reqres.in/img/faces/7-image.jpg"
                ),
                new JSONObject(
                        "id": 8,
                        "email": "lindsay.ferguson@reqres.in",
                        "first_name": "Lindsay",
                        "last_name": "Ferguson",
                        "avatar": "https://reqres.in/img/faces/8-image.jpg"
                ),
                new JSONObject(
                        "id": 9,
                        "email": "tobias.funke@reqres.in",
                        "first_name": "Tobias",
                        "last_name": "Funke",
                        "avatar": "https://reqres.in/img/faces/9-image.jpg"

                ),
                new JSONObject(
                        "id": 10,
                        "email": "byron.fields@reqres.in",
                        "first_name": "Byron",
                        "last_name": "Fields",
                        "avatar": "https://reqres.in/img/faces/10-image.jpg"

                ),
                new JSONObject(
                        "id": 11,
                        "email": "george.edwards@reqres.in",
                        "first_name": "George",
                        "last_name": "Edwards",
                        "avatar": "https://reqres.in/img/faces/11-image.jpg"
                ),
                new JSONObject(
                        "id": 12,
                        "email": "rachel.howell@reqres.in",
                        "first_name": "Rachel",
                        "last_name": "Howell",
                        "avatar": "https://reqres.in/img/faces/12-image.jpg"
                )

        ]
    }

    def "should return 200 for single user"() {
        when:
        def response = client.get(path:"/api/users/2")

        then:"server return 200 response"
        response.status == 200

        then:"verify the response attributes"
        response.data.data.equals(
                new JSONObject(
                        "id": 2,
                        "email": "janet.weaver@reqres.in",
                        "first_name": "Janet",
                        "last_name": "Weaver",
                        "avatar": "https://reqres.in/img/faces/2-image.jpg"
                )
        )

    }

    def "should return 404 for single users not found"() {
        when:
        def response = client.get(path:"/api/users/23")

        then:"server return 404 response"
        def result = thrown(HttpResponseException)

        then:"server return 404 response"
        result.statusCode == 404
    }

}
