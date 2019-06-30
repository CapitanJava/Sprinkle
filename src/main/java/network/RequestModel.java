package network;

public class RequestModel {

    private String username;
    private String password;

    public static RequestModel.Builder newBuilder(){
        return new RequestModel().new Builder();
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public class Builder{

        private Builder(){

        }

        public RequestModel.Builder setUsername(String username){
            RequestModel.this.username = username;
            return this;
        }

        public RequestModel.Builder setPassword(String password){
            RequestModel.this.password = password;
            return this;
        }

        public RequestModel build(){
            return RequestModel.this;
        }
    }

}
