package cn.xiehua.demo.security.token;

import cn.xiehua.demo.security.model.MyUserDetails;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.google.common.collect.Maps;

import java.util.Map;

public class JWTUtil {

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";

    public static <T> String sign(MyUserDetails userDetails) {
        try {
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = Maps.newHashMap();
            String jsonString = JSON.toJSONString(userDetails);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + 24*60*60*1000);
            return signer.sign(claims);
        } catch(Exception e) {
            return null;
        }
    }


    /**
     * get the object of jwt if not expired
     * @param jwt
     * @return POJO object
     */
    public static<T> T unsign(String jwt, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String,Object> claims= verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long)claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String)claims.get(PAYLOAD);
                    return JSON.parseObject(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args){
        MyUserDetails userDetails = new MyUserDetails("xiehua", "hahaha", "123456");
        String token = sign(userDetails);

        System.out.println(token);
        MyUserDetails myUserDetails = unsign(token,MyUserDetails.class);
        System.out.println(JSONObject.toJSONString(myUserDetails));
    }
}
