package algorithms.hillclimbsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class HillClimbSearch<STATE extends State, OPERATOR extends SearchOperator, EVALRET extends Comparable<EVALRET>, EVAL extends EvaluationFunction<STATE, EVALRET>> {

	private List<STATE> states = new LinkedList<>();
	private List<STATE> seenStates = new LinkedList<>();

	private EVAL evaluationFunction;

	private SearchOperatorApplier<STATE, OPERATOR> operatorApplier;

	public HillClimbSearch(EVAL evaluationFunction, SearchOperatorApplier<STATE, OPERATOR> operatorApplier) {
		this.evaluationFunction = evaluationFunction;
		this.operatorApplier = operatorApplier;
	}

	public boolean generalSearch(STATE initialState, STATE finalState, List<OPERATOR> searchOperators,
			int maxNumberOfTries) {

		int numberOfTries = 0;

		states.add(initialState);
		do {

			STATE state = states.remove(0);
			if (state.equals(finalState)) {
				System.out.println("tries: " + numberOfTries);
				return true;
			}
			seenStates.add(state);

			// find new states
			List<STATE> newStates = new ArrayList<>();
			for (OPERATOR searchOperator : searchOperators) {
				STATE newState = operatorApplier.applySearchOperator(state, searchOperator);
				if (!seenStates.contains(newState)) {
					newStates.add(newState);
				}
			}

			// sort new states
			Collections.sort(newStates, new StateComparator<STATE, EVALRET>(evaluationFunction, finalState));

			// add at the front of states list
			states.addAll(0, newStates);

			numberOfTries++;

			// System.out.println("try: " + numberOfTries);

		} while (!states.isEmpty() && numberOfTries < maxNumberOfTries);

		System.out.println("tries: " + numberOfTries);

		return false;
	}

	public static class StateComparator<STATE extends State, EVALRET extends Comparable<EVALRET>>
			implements Comparator<STATE> {

		private EvaluationFunction<STATE, EVALRET> evaluationFunction;
		private STATE finalState;

		public StateComparator(EvaluationFunction<STATE, EVALRET> evaluationFunction, STATE finalState) {
			this.evaluationFunction = evaluationFunction;
			this.finalState = finalState;
		}

		@Override
		public int compare(STATE stateLeft, STATE stateRight) {

			EVALRET ls = evaluationFunction.evaluate(stateLeft, finalState);
			EVALRET rs = evaluationFunction.evaluate(stateRight, finalState);
			return ls.compareTo(rs);
		}

	}
}
