let globalConfig = {
    state: {
        info: {}
    },
    mutations: {
        setGlobalConfig(state, data) {
            state.info = { ...state.info, ...data }
        }
    }
}

export default globalConfig