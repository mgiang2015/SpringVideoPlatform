import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import DataTable from "../components/DataTable";

export default function CourseManagement() {
    let navigate = useNavigate();
    const [courses, setCourses] = useState([])

    useEffect(() => {
        axios.get('http://localhost:8080/courses')
        .then(res => {
            console.log(res.data);
            const allCourses = res.data;
            setCourses(allCourses);
        })
    }, [])

    const handleRowClick = (params) => {
        navigate(`/courses/${params.row.id}/edit`)
    }

    const courseColumns = [
        {
            field: "id", headerName: "ID", flex: 1
        }, {
            field: "title", headerName: "Title", flex: 1
        }, {
            field: "description", headerName: "Description", flex: 1
        }, {
            field: "price", headerName: "Price", flex: 1
        }, {
            field: "updatedDate", headerName: "Updated At", flex: 1
        }
    ]

    return (
        <DataTable sx={{ width: "100%", padding: "2em" }} columns={courseColumns} rows={courses.map(
            course => {
                const updatedDate = new Date(course.updatedAt || course.createdAt);
                return {
                    id: course.id,
                    title: course.title,
                    description: course.description,
                    price: course.price,
                    updatedDate: "" + updatedDate.getDate() + '-' + (updatedDate.getMonth() + 1) + '-' + updatedDate.getFullYear(),
                }
            }
        )} onRowClick={handleRowClick} />
    )
}