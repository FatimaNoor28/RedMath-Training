<template>
    <div class="container">
        <h1 class="text-center">Add Account</h1>
        <form @submit.prevent="addAccount">
            <!-- Form fields here -->
            
            <div class="form-group">
                <label for="account_id">Account ID</label>
                <input type="text" id="id" v-model="account.id" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" v-model="account.name" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="amount">Password</label>
                <input type="text" id="password" v-model="account.password" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" id="email" v-model="account.email" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" id="address" v-model="account.address" class="form-control" required>
            </div>
            <!-- Other form fields -->
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</template>

<script>
import AccountService from '@/services/AccountService';

export default {
    name: 'AddAccount',
    data() {
        return {
            account: {
                id: '',
                name: '',
                password: '',
                email: '',
                address: ''
            }
        };
    },
    created() {
        this.addAccount();
        // this.editAccounts();
    },
    methods: {
        addAccount() {
            // Call your backend service to make the transaction
            AccountService.addAccount(this.account)
                .then((response) => {
                    // Handle the response, e.g., show a success message
                    console.log('Account Added:', response.data);
                    // Clear the form
                    this.account = {
                        id: '',
                        name: '',
                        password: '',
                        email: '',
                        address: ''
                    };
                })
                .catch((error) => {
                    // Handle errors, e.g., show an error message
                    console.error('Account failed:', error);
                });
        }
    }
};
</script>



<style scoped>
/* AddBalanceForm.css */

.container {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    background-color:lightgrey;
}

h1 {
    text-align: center;
    margin-bottom: 20px;
    font-weight: bold;
    color: #333;
}

.form-group {
    margin-bottom: 20px;
}

label {
    font-weight: bold; /* Make the label text bold */
    color: #333; /* Change the label text color */
}

.form-control {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
}

.btn-primary {
    background-color:#333;
    color: #fff;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
}

.btn-primary:hover {
    background-color: #0056b3;
}

</style>