<template>
  <div id="holder">
    <el-menu class="el-menu-demo" :default-active="3" mode="horizontal">
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
        <el-menu-item index="2">
          <router-link to="/user" v-slot="{ href, navigate }" custom>
            <a :href="href" @click="navigate"><i class="el-icon-user-solid"></i></a>
          </router-link>
        </el-menu-item>
      </el-tooltip>
      <el-tooltip content="Početna" placement="bottom">
        <el-menu-item index="3"><i class="el-icon-s-home"></i></el-menu-item>
      </el-tooltip>
    </el-menu>
    <div class="line"></div>
    <el-card v-if="!academicLife" class="box-card" shadow="always">
      <p style="text-align: center">
        Cilj ovog testiranja je da vam olakša izbor daljih studija i da vas što
        bolje usmeri ka željenim oblastima.
      </p>
      <el-button
        @click="$router.push('/big-five')"
        style="width: 100%"
        native-type="button"
        type="primary"
        >Započnite test</el-button
      >
    </el-card>
    <el-card v-if="academicLife" class="result-card">
      <div style="margin-bottom: 15px">
        <el-card class="in-blocks" shadow="always">
          <h4>Ovo su predloženi smerovi:</h4>
          <ul>
            <li v-for="major of academicLife.majorNames" :key="major">
              {{ major }}
            </li>
          </ul>
        </el-card>
        <div class="in-blocks">
          <el-card
            v-if="!isEmpty(academicLife.lifeCosts)"
            v-bind:style="{ height: height + '%', overflow: 'auto' }"
            shadow="always"
          >
            <div v-for="(value, key) in academicLife.lifeCosts" :key="key">
              <h4>Informacije o ceni života u {{ key }}:</h4>
              <p>
                Prosečna mesečna troškovi života: {{ value.lifeCost }} dinara
              </p>
              <p>Prosečna mesečna cena stanarine: {{ value.rent }} dinara</p>
            </div>
          </el-card>
          <el-card
            v-if="!isEmpty(academicLife.dormitories)"
            v-bind:style="{ height: height + '%', overflow: 'auto' }"
            style="height: 50%"
            shadow="always"
          >
            <h4>Lista mogućih domova u kojem bi ste mogli živeti:</h4>
            <div v-for="(value, key) in academicLife.dormitories" :key="key">
              <h4>{{ key }}:</h4>
              <ul>
                <li v-for="dorm in value" :key="dorm">{{ dorm }}</li>
              </ul>
            </div>
          </el-card>
        </div>
      </div>
    </el-card>
  </div>
</template>
<script>
export default {
  name: "Homepage",
  data: () => {
    return {
      academicLife: null,
      height: null,
    };
  },
  methods: {
    logout() {
      this.$store.dispatch("requestLogout").then(() => {
        this.$router.push({ path: "/login" });
      });
    },
    isEmpty(dict) {
      return Object.keys(dict).length == 0;
    },
  },
  mounted() {
    this.$store.dispatch("getAcademicLife").then(() => {
      this.academicLife = this.$store.state.academicLife;
      if (
        Object.keys(this.academicLife.dormitories).length == 0 ||
        Object.keys(this.academicLife.lifeCosts).length == 0
      ) {
        this.height = 100;
      } else {
        this.height = 50;
      }
      console.log(this.academicLife);
    });
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

.box-card {
  width: 300px;
  margin-top: 200px;
  margin-left: calc(50% - 150px);
  background-color: rgba(255, 255, 255, 0.9);
}

.result-card {
  width: 1000px;
  margin-top: 25px;
  margin-left: calc(50% - 500px);
  background-color: rgba(255, 255, 255, 0.9);
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