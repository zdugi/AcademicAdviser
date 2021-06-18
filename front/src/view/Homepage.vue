<template>
  <div id="holder">
    <el-menu class="el-menu-demo" mode="horizontal">
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
    </el-menu>
    <div class="line"></div>
    <el-card class="box-card" shadow="always">
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
  </div>
</template>
<script>
export default {
  name: "Homepage",
  data: () => {
    return {};
  },
  methods: {
    logout() {
      this.$store.dispatch("requestLogout").then(() => {
        this.$router.push({ path: "/login" });
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

.box-card {
  width: 300px;
  margin-top: 200px;
  margin-left: calc(50% - 150px);
  background-color: rgba(255, 255, 255, 0.85);
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