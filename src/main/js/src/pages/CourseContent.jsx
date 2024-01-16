import { Box, List, ListItem, ListItemButton, ListItemIcon, ListItemText } from "@mui/material";
import InboxIcon from '@mui/icons-material/Inbox';

export default function CourseContent() {
    return (
        <Box>
            <List>
                <ListItem>
                    <ListItemButton>
                        <ListItemIcon>
                            <InboxIcon />
                        </ListItemIcon>
                        <ListItemText primary="Chapter 1: The Beninging"/>
                    </ListItemButton>
                </ListItem>
            </List>
        </Box>
    )
}