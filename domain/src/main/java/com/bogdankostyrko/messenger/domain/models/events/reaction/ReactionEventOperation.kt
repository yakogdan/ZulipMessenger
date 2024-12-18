package com.bogdankostyrko.messenger.domain.models.events.reaction

sealed class ReactionEventOperation {
    data object Add : ReactionEventOperation()
    data object Remove : ReactionEventOperation()
    data object Unused : ReactionEventOperation()
}