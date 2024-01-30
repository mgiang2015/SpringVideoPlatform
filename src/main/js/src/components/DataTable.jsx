import { Box } from "@mui/material";
import { DataGrid } from '@mui/x-data-grid';
import { useNavigate } from "react-router-dom";

export default function DataTable({ columns, rows, sx, onRowClick }) {
    return (
        <DataGrid sx={sx}
            rows={rows}
            columns={columns}
            onRowClick={onRowClick}
            initialState={{
                pagination: {
                    paginationModel: { page: 0, pageSize: 5 },
                },
            }}
            pageSizeOptions={[5,10]}
        />
    )
}