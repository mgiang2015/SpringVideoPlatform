import { List, ListItem, ListItemButton, ListItemIcon, ListItemText } from "@mui/material"

export default function Sidebar({ items }) {
    return (
        <List>
            {
                items.map(item => {
                    return (
                        <ListItem key={item.text}>
                            <ListItemButton>
                                <ListItemIcon>
                                    {item.icon}
                                </ListItemIcon>
                                <ListItemText primary={item.text} />
                            </ListItemButton>
                        </ListItem>
                    )
                })
            }
        </List>
    )
}