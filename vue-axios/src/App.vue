<template>
  <div id="app">
    <header>
      <span>Testing Ajax Request with Axios in Vue</span>
    </header>
    <main>
      <form @submit.prevent="onSubmit">
        <p>
          <label for="id">id:</label>
          <input id="id" v-model.number="id" placeholder="id">
        </p>

        <p>
          <label for="firstname">FirstName:</label>
          <input id="firstname" v-model="firstname" placeholder="firstname">
        </p>
        <p>
          <label for="lastname">LastName:</label>
          <input id="lastname" v-model="lastname" placeholder="lastname">
        </p>
        <p>
          <label for="email">Email:</label>
          <input id="email" v-model="email" placeholder="email">
        </p>



        <p>
          <input type="submit" value="Submit">
        </p>

      </form>

      <h2>Click the button to get Users</h2>
      <button id="btn" class="" v-on:click="getUsers">Get Users</button>

      <div v-if="loading">
        <img src="../src/assets/loader.gif"/>
        Loading.....
      </div>

      <div class="wrapper">
        <div class="row">
          <div v-for="user in users" :key="user.id">
            <div class="col-md-4 cards">
              <div>
                <h3>{{ user.id }}</h3>
                <p>{{ user.firstname}}</p>
                <p>{{ user.lastname}}</p>
                <p>{{ user.email}}</p>

              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>  import axios from 'axios';

export default {
  name: 'app',
  data () {
    return {
      users: [],
      loading: false,
      id: null,
      firstname: null,
      lastname: null,
      email: null
    }
  },
  methods: {
    getUsers: function () {
      this.loading = true;
      axios.get("http://localhost:8090/users")
        .then((response)  =>  {
          this.loading = false;
          console.log(response);
          this.users= response.data;
        }, (error)  =>  {
          this.loading = false;
        })
    },
    onSubmit() {
      let user = {
        id: this.id,
        firstname: this.firstname,
        lastname: this.lastname,
        email: this.email,

      }

      axios.post("http://localhost:8090/users", user)
        .then((response)  =>  {

          console.log(response.data);
          console.log(response.status);

        }, (error)  =>  {
          console.log(error);
        })

     this.id= null;
      this.firstname = null;
      this.lastname = null;
      this.email = null;
    }
  },

}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
