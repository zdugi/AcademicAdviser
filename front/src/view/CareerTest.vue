<template>
    <div class="survey">
        <h3>Career Test</h3>
        <form>
            <ul>
              <v-container v-for="(pair, index) in this.$store.state.careerQuestions.questions" :key="pair.id">
                <li v-bind:class="{ 'gray': index % 2 == 1 }">
                  <span>{{ pair.questionA }}</span>
                  <div><input type="number" /></div>
                </li>
                <li v-bind:class="{ 'gray': index % 2 == 1 }">
                  <span>{{ pair.questionB }}</span>
                  <div><input type="number" /></div>
                </li>
              </v-container>
            </ul>
            <button class="float-right">Pošalji</button>
        </form>
    </div>
</template>
<script>
export default {
    name: 'CareerTest'
}
</script>

<style>
    .survey {
        width: 800px;
        margin: 50px auto;
        padding-bottom: 100px;
    }

    ul {
      list-style: none;
    }

    li {
      margin: 0;
      padding: 5px;
    }

    li div, li span {
      display: inline;
    }

    .gray {
      background-color: #f9f9f9;
    }

    input {
      width: 70px !important;
      margin-left: 50px;
      background-color: #fff !important;
    }

</style>