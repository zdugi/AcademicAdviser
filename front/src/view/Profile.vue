<template>
  <div id="holder">
    <el-menu class="el-menu-demo" :default-active="2" mode="horizontal">
      <span class="logo">
        <img src="../assets/student.png" width="40" />
        <h4>Academic Advisor</h4>
      </span>
      <el-tooltip content="Odjavite se" placement="bottom">
        <el-menu-item index="1" v-on:click="logout"
          ><i class="el-icon-right"></i
        ></el-menu-item>
      </el-tooltip>
      <el-tooltip content="Profil" placement="bottom">
        <el-menu-item index="2"
          ><i class="el-icon-user-solid"></i
        ></el-menu-item>
      </el-tooltip>
      <el-tooltip content="Početna" placement="bottom">
        <el-menu-item index="3"
          ><router-link to="/" v-slot="{ href, navigate }" custom>
            <a :href="href" @click="navigate"><i class="el-icon-s-home"></i></a>
          </router-link></el-menu-item>
      </el-tooltip>
    </el-menu>
    <div class="line"></div>
    <div class="profile-card">
      <el-card class="box-card" shadow="always">
        <el-tabs v-model="activeTab">
          <el-tab-pane label="Profil" name="profil">
            <div>
              <form @submit="submit">
                <p>Email adresa</p>
                <el-input
                  placeholder="Email adresa"
                  v-model="profile.emailAddress"
                  disabled
                >
                </el-input>
                <p>Ime</p>
                <el-input
                  placeholder="Ime"
                  v-model="profile.firstName"
                  clearable
                >
                </el-input>
                <p>Prezime</p>
                <el-input
                  placeholder="Prezime"
                  v-model="profile.lastName"
                  clearable
                >
                </el-input>
                <p>Prosek iz srednje škole</p>
                <el-input-number
                  style="width: 100px"
                  v-model="profile.grade"
                  :controls="false"
                  :precision="2"
                  :max="5"
                  :min="1"
                ></el-input-number>
                <el-button
                  native-type="submit"
                  type="primary"
                  style="width: 100%"
                  >Ažurirajte profil</el-button
                >
              </form>
            </div>
          </el-tab-pane>
          <el-tab-pane label="Željeni grad" name="desired">
            <p>Željeni grad u kom bi ste studirali</p>
            <el-select
              v-model="desiredCity"
              placeholder="Izaberite grad"
              @change="setDesired()"
            >
              <el-option
                v-for="(value, key) in cities"
                :key="key"
                :label="value"
                :value="key"
              >
              </el-option>
            </el-select>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </div>
  </div>
</template>
<script>
export default {
  name: "Profile",
  data: () => {
    return {
      profile: {
        emailAddress: "",
        firstName: "",
        lastName: "",
        grade: "",
      },
      cities: {},
      activeTab: "profil",
      desiredCity: null,
    };
  },
  methods: {
    logout() {
      this.$store.dispatch("requestLogout").then(() => {
        this.$router.push({ path: "/login" });
      });
    },
    submit(event) {
      event.preventDefault();

      this.$store
        .dispatch("updateProfile", this.profile)
        .then(() => {
          this.$notify({
            title: "Success",
            message: "Uspešno ste se ažurirali Vaš profil.",
            type: "success",
            duration: 2500,
          });
        })
        .catch(() => {
          this.$notify.error({
            title: "Error",
            message: "Pogrešno ste uneli podatke.",
            duration: 2500,
          });
        });
    },
    setDesired() {
      this.$store.dispatch("setDesired", this.desiredCity);
    },
  },
  mounted() {
    this.$store
      .dispatch("getProfile")
      .then(() => {
        this.profile = this.$store.state.profile;
      })
      .catch(() => {
        this.$notify.error({
          title: "Error",
          message: "Neočekivana greška.",
          duration: 2500,
        });
      });

    this.$store
      .dispatch("getCities")
      .then(() => {
        this.cities = this.$store.state.cities;
      })
      .catch(() => {
        this.$notify.error({
          title: "Error",
          message: "Neočekivana greška.",
          duration: 2500,
        });
      });

    this.desiredCity = this.$store.getters.getDesiredCity;
  },
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

.profile-card {
  width: 400px;
  margin-top: 20px;
  margin-left: calc(50% - 200px);
}

.in-blocks:first-child {
  width: 50% !important;
  display: inline-block !important;
  overflow: auto;
}

.in-blocks {
  width: 50%;
  height: 500px;
  display: inline-block;
}

li {
  font-size: 15px;
}

p {
  margin: 0;
}

.el-menu-item {
  float: right !important;
}

i {
  color: black !important;
  font-size: 25px !important;
}

.logo > * {
  display: inline-block;
}

.logo > h4 {
  position: absolute;
  margin-top: 16px;
}

.logo > img {
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 15px;
}
</style>