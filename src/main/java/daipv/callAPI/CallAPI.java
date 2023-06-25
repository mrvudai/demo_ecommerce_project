package daipv.callAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import daipv.model.RoleName;
import daipv.model.Roles;
import daipv.model.Users;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CallAPI {
    public static void main(String[] args) {
        docall2();
    }


    public static void docall2(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/auth/call");

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Đọc dữ liệu từ phản hồi
                String responseBody = EntityUtils.toString(entity);
                Set<Users> users = new LinkedHashSet<>();

                // Phân tích cú pháp JSON
                JSONParser parser = new JSONParser();
                JSONObject jsonResponse = (JSONObject) parser.parse(responseBody);

                // Lấy mảng người dùng từ thuộc tính "content"
                JSONArray contentArray = (JSONArray) jsonResponse.get("content");

                // Xử lý mỗi đối tượng người dùng trong mảng
                for (Object userObj : contentArray) {
                    JSONObject userJson = (JSONObject) userObj;

                    // Lấy các thuộc tính của người dùng
                    Long id = ((Long) userJson.get("id"));
                    String userName = (String) userJson.get("userName");
                    String email = (String) userJson.get("email");
                    String password = (String) userJson.get("password");

                    // Xử lý các thuộc tính khác của người dùng
                    JSONArray rolesArray = (JSONArray) userJson.get("roles");
                    Set<Roles> setRole = new HashSet<>();
                    for (Object obj: rolesArray) {
                        JSONObject roleJson = (JSONObject) obj;
                         Long idRole = (Long) roleJson.get("id");
                        RoleName roleName = RoleName.valueOf(String.valueOf(roleJson.get("roleName")));
                        setRole.add(new Roles(idRole, roleName));
                        System.out.println(obj);
                    }

                    // ... xử lý các thông tin khác

                    // In thông tin người dùng
                    System.out.println("ID: " + id);
                    System.out.println("Username: " + userName);
                    System.out.println("Email: " + email);
                    System.out.println("Password: " + password);
                    System.out.println();
                    Users user = new Users(id, userName, password, email, setRole);
                    users.add(user);
                }
                System.out.println(users);
            }

            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
