<template>
  <div class="survey">
    <h3>Big Five</h3>
    <form @submit="submit">
      <table>
        <thead>
          <th style="width: 400px">&nbsp;</th>
          <th>Ne slažem se</th>
          <th>U manjoj meri</th>
          <th>Neutralno</th>
          <th>U većoj meri</th>
          <th>Slažem se</th>
        </thead>
        <tbody>
          <tr v-for="question in survey" :key="question.id">
            <td>
              <b>{{ question.text }}</b>
            </td>
            <td>
              <input
                type="radio"
                v-bind:name="question.id"
                value="1"
                v-model="question.picked"
                v-on:change="answerQuickSend(question.id, 1)"
              />
            </td>
            <td>
              <input
                type="radio"
                v-bind:name="question.id"
                value="2"
                v-model="question.picked"
                v-on:change="answerQuickSend(question.id, 2)"
              />
            </td>
            <td>
              <input
                type="radio"
                v-bind:name="question.id"
                value="3"
                v-model="question.picked"
                v-on:change="answerQuickSend(question.id, 3)"
              />
            </td>
            <td>
              <input
                type="radio"
                v-bind:name="question.id"
                value="4"
                v-model="question.picked"
                v-on:change="answerQuickSend(question.id, 4)"
              />
            </td>
            <td>
              <input
                type="radio"
                v-bind:name="question.id"
                value="5"
                v-model="question.picked"
                v-on:change="answerQuickSend(question.id, 5)"
              />
            </td>
          </tr>
        </tbody>
      </table>
      <button :disabled="filled" class="float-right">Pošalji</button>
    </form>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  name: "BigFiveSurvey",
  props: {},
  data: () => {
    return {
      survey: [],
    };
  },
  computed: {
    filled() {
      return this.survey.filter(s => !s.picked).length > 0
    }
  },
  methods: {
    submit(event) {
      event.preventDefault();

      let payload = {
        answers: [],
      };

      // check
      for (let q of this.survey) {
        if (q.picked == null) {
          alert("Fill all!");
          return;
        }

        payload["answers"].push({
          id: parseInt(q.id),
          score: parseInt(q.picked),
        });
      }

      this.$store.dispatch("submitBigFiveSurvey", payload).then(() => {
        // redirect
        console.log(this.$store.state.careerQuestions.questions[0].questionA);
        this.$router.push({ path: "career-test" });
      });
    },
    answerQuickSend(id, score) {
      axios.post("http://localhost:8080/api/big-five/tracker", {id: id, score: score})
    }
  },
  mounted() {
    this.$store.dispatch("getBigFiveTest").then(() => {
      this.survey = this.$store.state.bigFiveQuestions;
    });
  },
};
</script>
<style scoped>
.survey {
  width: 1000px;
  margin: 50px auto;
  padding-bottom: 100px;
}
</style>