import { createStore } from 'vuex'
import axios from 'axios';


export const store = createStore({
  state() {
    return {
      careerQuestions: []
    }
  },
  mutations: {
    setCareerQuestions(state, questions) {
      state.careerQuestions = questions
    }
  },
  actions: {
    submitBigFiveSurvey({ commit, state }, survey) {
      console.log(commit, state, survey)
      return new Promise((resolve, reject) => {
        axios.post('http://localhost:8080/api/big-five/survey', survey).then(
          (response) => {
            commit('setCareerQuestions', response.data)
            resolve(response.data)
          }
        ).catch(() => {
          reject()
        })
      })
    },
    submitCareerTest({ commit, state }, careerTest) {
      console.log(commit, state, careerTest)
      return new Promise((resolve, reject) => {
        axios.post('http://localhost:8080/api/career-test', careerTest).then(
          (response) => {
            console.log('Poslato' + response);
          }
        ).catch(() => {
          reject()
        })
      })
    }
  }
})