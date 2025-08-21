package com.elton.xdordersprototipojetpackcompose.viewModel;


import static java.util.Collections.emptyList;
import androidx.lifecycle.ViewModel;
import com.elton.xdordersprototipojetpackcompose.data.local.DAO;
import com.elton.xdordersprototipojetpackcompose.domain.model.Table;
import java.util.List;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;

public class TablesViewModel extends ViewModel {
    private final DAO dao;
    private final MutableStateFlow<List<Table>> _mesas = kotlinx.coroutines.flow.StateFlowKt.MutableStateFlow(emptyList());
    public final StateFlow<List<Table>> mesas = _mesas;

    public TablesViewModel(DAO dao) {
        this.dao = dao;
        loadTables();
    }

    public void loadTables() {
        new Thread(() -> {
            List<Table> tables = dao.getAllTables();
            _mesas.setValue(tables);
        }).start();
    }
}