import axios from 'axios';

const LOGIN_API_BASE_URL = 'http://localhost:8080/api/v1/account/login';

class LoginService{

    async getLogin(user){
        return axios.post(LOGIN_API_BASE_URL, user);
        // return data;   
    }
}

export default new LoginService();


// mounted() {
//     console.log("mounted() called.......");
// },
// methods: {
//      LoginData()
//      {
//       axios.post("http://localhost:8080/api/v1/login", this.user)
//       .then(
//         ({data})=>{
//          console.log(data);
//          try {
//          if (data.message === "User Name not exits") 
//              {
//               alert("User name not exits");
              
//                }
//                 else if(data.message == "Login Success")
//                {
              
//                 this.$App.push({ name: 'Accounts' })
//                }
//                 else 
//                { 
//                    alert("Incorrect User Name and Password not match");
//                }

//                } catch (err) {
//                    console.error("An error occurred:", err);
//                    alert("Error: Unable to complete the request. Please try again later.");
//                }    
//         }
//       )
//      }
//}