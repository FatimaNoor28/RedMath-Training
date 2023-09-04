<template>
    <div class="row">

        <div class="col-sm-4">
            <h2 align="center"> Login</h2>

            <form @submit.prevent="LoginData">


                <div class="form-group" align="left">
                    <label>User Name</label>
                    <input type="name" v-model="user.userName" class="form-control" placeholder="User Name" align="center">
                </div>


                <div class="form-group" align="left">
                    <label>Password</label>
                    <input type="password" v-model="user.password" class="form-control" placeholder="Password" align="center">
                </div>
                <br />

                <button type="submit" class="btn btn-primary">Login</button>
            </form>
        </div>
    </div>
</template>
   
<script>
//    import Vue from 'vue';
//    import axios from 'axios';
import LoginService from '../services/LoginService';

//  Vue.use(axios)
export default {
    name: 'LoginDetail',
    data() {
        return {
            result: {
                message: '',
                status: false
            },
            user: {
                userName: '',
                password: ''
            }
        }
    },
    methods: {
        async LoginData() {
            const result = await LoginService.getLogin(this.user);

            try {
                console.log("Response from server:", result);
                if (result.data.status===true ) {
                    alert("User and Password are correct");
                    this.$router.push({name: 'Home'});
                }else {
                    alert(result.data.message);
                }
            } catch (error) {
                console.error("An error occurred:", error);
                alert("Error: Unable to complete the request. Please try again later.");
            }
        },
        created() {
            this.LoginData();
        },
    }
}
</script>