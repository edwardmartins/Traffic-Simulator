package view;

import java.util.List;

import javax.swing.SwingUtilities;

import control.Controller;
import events.Event;
import exceptions.SimulationError;
import model.map.RoadMap;
import model.objects.Road;

@SuppressWarnings("serial")
public class RoadTableModel extends TableModel<Road>{

	public RoadTableModel(String[] columnIdEventos, Controller ctrl) {
		super(columnIdEventos, ctrl);
		
	}
	
	// Necesario para que se visualicen los datos
	// --------------------------------------------------------------
	public Object getValueAt(int indiceFil, int indiceCol) {
		
		Object s = null;
		switch (indiceCol) {
		case 0:
			s = this.list.get(indiceFil).getId();
			break;
		case 1:
			s = this.list.get(indiceFil).getOriginJunction();
			break;
		case 2:
			s = this.list.get(indiceFil).getDestinationJunction();
			break;
		case 3:
			s = this.list.get(indiceFil).getRoadLength();
			break;
		case 4:
			s = this.list.get(indiceFil).getMaxSpeed();
			break;
		case 5:
			s = this.list.get(indiceFil).getVehicles();
			break;
			// default
		default: assert (false);
		}
		return s;
	}

	// Observadores
	// ----------------------------------------------------------------------------
	@Override
	public void simulatorError(int tiempo, RoadMap map, List<Event> events,
			SimulationError e) {}

	@Override
	public void advance(int tiempo, RoadMap mapa, List<Event> events) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				list = mapa.getRoads(); // devuelve la lista de carreteras
				fireTableStructureChanged(); // para repintar la estructura 
				// (Como avisar a la vista JTable de que los elementos han cambiado)
			}
		});
		
	}

	@Override
	public void addEvent(int tiempo, RoadMap mapa, List<Event> events) {}

	@Override
	public void reset(int tiempo, RoadMap mapa, List<Event> events) {
		this.list = mapa.getRoads();
		this.fireTableStructureChanged();
	}

}