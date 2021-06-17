<template>
  <div id="holder">
    <div class="login-card">
      <el-card class="box-card" shadow="always">
        <div class="logo">
          <img src="../assets/student.png" width="100" />
          <h3>Academic Advisor</h3>
        </div>
        <div>
          <form @submit="submit">
            <p>Email adresa</p>
            <el-input
              placeholder="Email adresa"
              v-model="loginForm.emailAddress"
              clearable
            >
            </el-input>
            <p>Šifra</p>
            <el-input
              placeholder="Šifra"
              v-model="loginForm.password"
              show-password
            >
            </el-input>
            <el-button native-type="submit" type="primary" style="width: 100%"
              >Prijavite se</el-button
            >
          </form>
        </div>
        <div class="link">
          <router-link to="/registration" v-slot="{ href, navigate }" custom>
            <a :href="href" @click="navigate">Nemate nalog? Registrujte se</a>
          </router-link>
        </div>
      </el-card>
    </div>
  </div>
</template>
<script>
export default {
  name: "Login",
  data: () => {
    return {
      loginForm: {
        emailAddress: "",
        password: "",
      },
    };
  },
  methods: {
    submit(event) {
      event.preventDefault();

      this.$store
        .dispatch("requestAuth", this.loginForm)
        .then(() => {
          this.$router.push("/");
        })
        .catch(() => {
          this.$notify.error({
            title: "Error",
            message: "Pogrešni kredencijali.",
            duration: 2500
          });
        });
    },
  },
  mounted() {},
};
</script>

<style scoped>
#holder {
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
  background-image: url("../assets/academicadvisor.png");
  background-repeat: no-repeat;
  background-size: cover;
  background-attachment: fixed;
  background-position: center;
}

.login-card {
  width: 400px;
  margin-top: 100px;
  margin-left: calc(50% - 200px);
}

.logo {
  text-align: center;
}

.link {
  text-align: right;
}

p {
  margin-bottom: 0;
}
</style>