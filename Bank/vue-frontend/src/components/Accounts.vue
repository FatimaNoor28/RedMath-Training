<template>
    <div class="container">

        <h1 class="text-center"> Accounts List</h1>

        <table class="table table-striped table-dark">
            <thead>
                <tr>
                    <th> Account Id</th>
                    <th> Name</th>
                    <th> Password</th>
                    <th> Email</th>
                    <th> Address</th>
                </tr>

            </thead>
            <tbody>
                <tr v-for="Account in Accounts" v-bind:key="Account.id">
                    <td> {{ Account.id }}</td>
                    <td> {{ Account.name }}</td>
                    <td> {{ Account.password }}</td>
                    <td> {{ Account.email }}</td>
                    <td> {{ Account.address }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import AccountService from '../services/AccountService';

export default {
    name: 'AccountsList',
    data() {
        return {
            Accounts: {},
            Account: {
                id: '',
                name: '',
                password: '',
                email: '',
                address: ''
            }
        }
    },
    created() {
        this.getAccounts();
        // this.editAccounts();
    },
    mounted() {
        console.log("mounted() called.......");

    },
    methods: {
        getAccounts() {
            AccountService.getAccounts().then((response) => {
                this.Accounts = response.data;
            });
        },
        // save() {
        //     if (this.customer.customerid == '') {
        //         this.saveAccount();
        //     }
        //     else {
        //         this.updateAccount();
        //     }
        // },
        // saveAccount() {
        //     AccountService.saveAccount(this.Account).then((response)=>{
        //         this.Account = response.data;
        //     }

        //     axios.post("http://localhost:8084/api/v1/customer/save", this.customer)
        //         .then(
        //             ({ data }) => {
        //                 alert("saveddddd");
        //                 this.customer.customerid = '';
        //                 this.customer.customername = '';
        //                 this.customer.customeraddress = '',
        //                     this.customer.mobile = ''
        //                 this.CustomereLoad();
        //             }
        //         )
        // },
    }
}
</script>