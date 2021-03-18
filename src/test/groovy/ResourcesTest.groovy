import groovyx.net.http.RESTClient
import net.sf.json.JSONObject
import spock.lang.Shared
import spock.lang.Specification

class ResourcesTest extends  Specification {

    @Shared
    def client = new RESTClient("https://reqres.in/")

    def "should return 200 for list of resource "() {
        when:
        def response = client.get(path:"/api/unknown")

        then: "server returns 200 response"
        response.status == 200
        then: "verify the response attributes"
        response.data.page == 1
        response.data.total == 12
        response.data.total_pages == 2
        response.data.data.size() == 6
        response.data.data == [
            new JSONObject(
                    "id": 1,
                    "name": "cerulean",
                    "year": 2000,
                    "color": "#98B2D1",
                    "pantone_value": "15-4020"
            ),
            new JSONObject(
                    "id": 2,
                    "name": "fuchsia rose",
                    "year": 2001,
                    "color": "#C74375",
                    "pantone_value": "17-2031"
            ),
            new JSONObject(
                    "id": 3,
                    "name": "true red",
                    "year": 2002,
                    "color": "#BF1932",
                    "pantone_value": "19-1664"
            ),
            new JSONObject(
                    "id": 4,
                    "name": "aqua sky",
                    "year": 2003,
                    "color": "#7BC4C4",
                    "pantone_value": "14-4811"

            ),
            new JSONObject(
                    "id": 5,
                    "name": "tigerlily",
                    "year": 2004,
                    "color": "#E2583E",
                    "pantone_value": "17-1456"
            ),
            new JSONObject(
                    "id": 6,
                    "name": "blue turquoise",
                    "year": 2005,
                    "color": "#53B0AE",
                    "pantone_value": "15-5217"
            )
        ]
    }

}
