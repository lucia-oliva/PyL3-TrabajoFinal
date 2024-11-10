package unlar.edu.ar.paradigma.controllers;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import unlar.edu.ar.paradigma.controladores.ZonaCuerpoController;
import unlar.edu.ar.paradigma.objetos.ZonaCuerpo;

public class ZonaCuerpoControllerTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    private ZonaCuerpoController zonaCuerpoController;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        zonaCuerpoController = new ZonaCuerpoController();
        zonaCuerpoController.setConexion(mockConnection);
    }

    @Test
    public void testExtraerTodo() throws SQLException {
        // Prepare mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id_zona")).thenReturn(1);
        when(mockResultSet.getInt("codigo")).thenReturn(1);
        when(mockResultSet.getInt("izqDer")).thenReturn(1);

        // Call the method
        List<ZonaCuerpo> listaZonas = zonaCuerpoController.extraerTodo();

        // Assertions
        assertNotNull(listaZonas);
        assertEquals(1, listaZonas.size());
        assertEquals(1, listaZonas.get(0).getId_zona());
        assertEquals(1, listaZonas.get(0).getCodigo());
        assertEquals(1, listaZonas.get(0).getIzqder());
    }

    @Test
    public void testExtraer() throws SQLException {
        // Prepare mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id_zona")).thenReturn(1);
        when(mockResultSet.getInt("codigo")).thenReturn(1);
        when(mockResultSet.getInt("izqDer")).thenReturn(1);

        // Call the method
        ZonaCuerpo zona = zonaCuerpoController.extraer(1);

        // Assertions
        assertNotNull(zona);
        assertEquals(1, zona.getId_zona());
        assertEquals(1, zona.getCodigo());
        assertEquals(1, zona.getIzqder());
    }

    @Test
    public void testCrear() throws SQLException {
        // Prepare mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1); // Simulate 1 row affected

        // Create ZonaCuerpo object
        ZonaCuerpo zonaCuerpo = new ZonaCuerpo(1, 1, 1);

        // Call the method
        boolean result = zonaCuerpoController.crear(zonaCuerpo);

        // Assertions
        assertTrue(result);
        verify(mockStatement, times(1)).executeUpdate(); // Verify if executeUpdate was called
    }

    @Test
    public void testModificar() throws SQLException {
        // Prepare mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1); // Simulate 1 row affected

        // Create ZonaCuerpo object
        ZonaCuerpo zonaCuerpo = new ZonaCuerpo(1, 1, 1);

        // Call the method
        boolean result = zonaCuerpoController.modificar(zonaCuerpo);

        // Assertions
        assertTrue(result);
        verify(mockStatement, times(1)).executeUpdate(); // Verify if executeUpdate was called
    }

    @Test
    public void testEliminar() throws SQLException {
        // Prepare mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1); // Simulate 1 row affected

        // Create ZonaCuerpo object
        ZonaCuerpo zonaCuerpo = new ZonaCuerpo(1, 1, 1);

        // Call the method
        boolean result = zonaCuerpoController.eliminar(zonaCuerpo);

        // Assertions
        assertTrue(result);
        verify(mockStatement, times(1)).executeUpdate(); // Verify if executeUpdate was called
    }
}
