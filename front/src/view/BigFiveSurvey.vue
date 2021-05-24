<template>
    <div class="survey">
        <h3>Big Five</h3>
        <form @submit="submit">
            <table>
                <thead>
                    <th style="width: 400px;">&nbsp;</th>
                    <th>Ne slažem se</th>
                    <th>U manoj meri</th>
                    <th>Neutralno</th>
                    <th>U većoj meri</th>
                    <th>Slažem se</th>
                </thead>
                <tbody>
                    <tr v-for="question in survey" :key="question.id">
                        <td><b>{{ question.text }}</b></td>
                        <td><input type="radio" v-bind:name="question.id" value="1" v-model="question.picked" /></td>
                        <td><input type="radio" v-bind:name="question.id" value="2" v-model="question.picked"/></td>
                        <td><input type="radio" v-bind:name="question.id" value="3" v-model="question.picked"/></td>
                        <td><input type="radio" v-bind:name="question.id" value="4" v-model="question.picked"/></td>
                        <td><input type="radio" v-bind:name="question.id" value="5" v-model="question.picked"/></td>
                    </tr>
                </tbody>
            </table>
            <button class="float-right">Pošalji</button>
        </form>
    </div>
</template>
<script>
import axios from 'axios';

export default {
  name: 'BigFiveSurvey',
  props: {
  },
  data: () => {
      return {
          survey: []
      }
  },
  methods: {
      submit (event) {
          event.preventDefault()

          let payload = {
              'answers': []
          }

          // check
          for (let q of this.survey) {
              if (q.picked == null) {
                  alert('Fill all!')
                  return
              }

              payload['answers'].push({
                  'id': parseInt(q.id),
                  'score': parseInt(q.picked)
              })
          }

          this.$store.dispatch('submitBigFiveSurvey', payload).then(() => {
              // redirect
              console.log(this.$store.state.careerQuestions.questions[0].questionA)
              this.$router.push({'path': 'career-test'})
          })
      }
  },
  mounted() {
      axios.get('http://localhost:8080/api/big-five/survey').then(
          response => {
              for (let question of response.data.questionDTOList) {
                this.survey.push({
                    id: question.id,
                    text: question.text,
                    picked: Math.floor(Math.random() * 2 + 3)
                })
              }
          }
      )
  }
}
</script>
<style scoped>
    .survey {
        width: 1000px;
        margin: 50px auto;
        padding-bottom: 100px;
    }
</style>