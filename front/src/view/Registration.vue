<template>
  <div id="holder">
    <div
      class="login-card"
      v-loading="loading"
      element-loading-text="Sačekajte da se obrade podaci"
      element-loading-background="rgba(0, 0, 0, 0.6)"
    >
      <el-card class="box-card" shadow="always">
        <div class="logo">
          <img src="../assets/student.png" width="100" />
          <h3>Academic Advisor</h3>
        </div>
        <form @submit="submit">
          <div class="f-container">
            <p>Email adresa</p>
            <el-input
              placeholder="Email adresa"
              v-model="registForm.emailAddress"
              clearable
            >
            </el-input>
            <p>Šifra</p>
            <el-input
              placeholder="Šifra"
              v-model="registForm.password"
              show-password
            >
            </el-input>
            <p>Ime</p>
            <el-input
              placeholder="Ime"
              v-model="registForm.firstName"
              clearable
            >
            </el-input>
            <p>Prezime</p>
            <el-input
              placeholder="Prezime"
              v-model="registForm.lastName"
              clearable
            >
            </el-input>
            <p>Pol</p>
            <div>
              <el-radio v-model="registForm.gender" label="0">Muški</el-radio>
            </div>
            <div>
              <el-radio v-model="registForm.gender" label="1">Ženski</el-radio>
            </div>
            <p>Prosek iz srednje škole</p>
            <el-input-number
              style="width: 100px"
              v-model="registForm.grade"
              :controls="false"
              :precision="2"
              :max="5"
              :min="1"
            ></el-input-number>
          </div>
          <el-button native-type="submit" type="primary" style="width: 100%"
            >Registrujte se</el-button
          >
        </form>
        <div class="link">
          <router-link to="/login" v-slot="{ href, navigate }" custom>
            <a :href="href" @click="navigate"
              >Već ste registrovani? Prijavite se</a
            >
          </router-link>
        </div>
      </el-card>
    </div>
  </div>
</template>
<script>
export default {
  name: "Registration",
  data: () => {
    return {
      registForm: {
        emailAddress: "",
        password: "",
        firstName: "",
        lastName: "",
        gender: null,
        grade: null,
      },
      loading: false,
    };
  },
  methods: {
    submit(event) {
      this.loading = true;
      event.preventDefault();

      this.$store
        .dispatch("requestRegistration", this.registForm)
        .then(() => {
          this.$notify({
            title: "Success",
            message: "Uspešno ste se registrovali.",
            type: "success",
            duration: 2500,
            onClose: () => {
              this.$router.push("/login");
            },
          });
        })
        .catch(() => {
          this.$notify.error({
            title: "Error",
            message: "Pogrešno ste uneli podatke.",
            duration: 2500,
          });
          this.loading = false;
        });
    },
  },
  mounted() {},
};
</script>

<style>
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
  margin-top: 50px;
  margin-left: calc(50% - 200px);
}

.logo {
  text-align: center;
}

.link {
  text-align: right;
}

.f-container {
  overflow: auto;
  height: 300px;
  margin-bottom: 15px;
  padding: 2px;
}

p {
  margin-bottom: 0;
}
</style>